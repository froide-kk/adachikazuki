<html>
    <Link rel ="stylesheet" type="text/css" href="style.css"/>
    <body>
    <form method="get" action="/book/search>
    <h1>検索</h1>
            <p>id：<br>
            <input type="text" name="bookid" size="30"></p>
             <p>タイトル：<br>
             <input type="text" name="bookname" size="30"></p>
             <p>著者：<br>
             <input type="text" name="bookauthor" size="30"></p>
             <p><input type="submit"  value="検索する"></p>
        </form>
        <h1>登録</h1>
     <input type="button" class="button015" onclick="location.href='http://localhost:4567/newBook'" value="登録">
     <h1>データ一覧</h1>
       <table border="1">

             <tr>
                <th>id</th>
                <th>名前</th>
                <th>著者</th>
                <th></th>
                <th></th>

             </tr>

            <#list books as b>
                <tr>
             　     <td>${b.id}</td>
             　      <td>${b.name}</td>
                    <td>${b.author}　</td>
                    <td>
                    <form action=updatabook method="get" >
                        <input hidden ="text" name="update" value=${b.id}></p>
                        <input type="submit" value="更新">
                    </form>
                    <form action=book method="post" >
                         <input hidden ="text" name="delete" value=${b.id}></p>
                         <input type="submit" value="削除">
                    </form>

                    </td>
                </tr>

           </#list>
        </table>　
	 </body>
</html>