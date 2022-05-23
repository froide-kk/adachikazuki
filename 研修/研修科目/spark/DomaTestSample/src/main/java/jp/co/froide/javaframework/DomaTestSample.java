package jp.co.froide.javaframework;
import jp.co.froide.javaframework.db.*;
import org.seasar.doma.jdbc.tx.TransactionManager;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.*;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomaTestSample {
    public static void main(String[] args) {
        staticFiles.location("/public");
        BookDao bookdao = new BookDaoImpl(DbConfig.singleton());
        TransactionManager tm = DbConfig.singleton().getTransactionManager();
        Map<String, Object> attribute = new HashMap<>();

        //データベース一覧表示
        get("/book", (req, res) -> {

            tm.required(() -> {
                List<Book> books = bookdao.selectAll();
                attribute.put("books", books);
            });
            return  new FreeMarkerEngine().render(new ModelAndView(attribute, "book.ftl"));});

        //登録画面の表示
        get("/newBook", (req, res) -> {
            //
            return  new FreeMarkerEngine().render(new ModelAndView(attribute, "newBook.ftl"));});


        //登録処理
        post("/newBook", (req, res) -> {

            //追加するデータの登録
            Book newbook = new Book();
            String name = req.queryParams("bookname");
            String author = req.queryParams("bookauthor");
            newbook.setName(name);
            newbook.setAuthor(author);
            tm.required(() -> {
            bookdao.insert(newbook);
            });
            return  new FreeMarkerEngine().render(new ModelAndView(newbook, "newedbook.ftl"));});
        //登録完了画面
        get("/newedbook", (req, res) -> {
            //
            return  new FreeMarkerEngine().render(new ModelAndView(attribute, "newedbook.ftl"));});

        //更新画面の表示
        get("/updatabook", (req, res) -> {
            //
            return  new FreeMarkerEngine().render(new ModelAndView(attribute, "updatabook.ftl"));});

        //更新処理
        post("/updatabook", (req, res) -> {
            //idの受け取り
            String strId = req.queryParams("update");
            //受け取ったstrId(str)を数値型に変換
            Number number = NumberFormat.getInstance().parse(strId);
            int id = number.intValue();
            //更新する
            tm.required(() -> {
                //指定idをセレクトする
                Book updata = bookdao.selectById(id);
                //タイトル著者名の変更値を受け取る
                String named = req.queryParams("bookname");
                String authored = req.queryParams("bookauthor");
                updata.setName(named);
                updata.setAuthor(authored);
                bookdao.updata(updata);
            });

            return  new FreeMarkerEngine().render(new ModelAndView(attribute, "updatedbook.ftl"));});
        //更新完了画面
        get("/updatedbook", (req, res) -> {
            //
            return  new FreeMarkerEngine().render(new ModelAndView(attribute, "updatedbook.ftl"));});
        //削除画面の表示
        post("/book", (req, res) -> {
            //idの受け取り
            String strId = req.queryParams("delete");
            //受け取ったstrId(str)を数値型に変換
            Number number = NumberFormat.getInstance().parse(strId);
            int id = number.intValue();

            //削除する
            tm.required(() -> {
                Book delete = bookdao.selectById(id);
                bookdao.delete(delete);
            });
            res.redirect("/book");
            return  res;
            });
        get(("/book/search",(req, res) -> {
            //検索したデータの表示
            tm.required(() -> {
                List<Book> books = bookdao.selectAll();
                attribute.put("books", books);
            });
            return  new FreeMarkerEngine().render(new ModelAndView(newbook, "book.ftl"));
        });
    }
}

