<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>ユーザー登録</title>
</head>
<body>
<form method="post" action="/user/add">
    <input type="text" name="name" placeholder="名前を入力"/>
    <p style="color: #FF0000">${nameError}</p>
    <input type="password" name="password" placeholder="パスワードを入力"/>
    <p style="color: #FF0000">${passwordError}</p>
    <input type="password" name="passwordConfirm" placeholder="確認用パスワードを入力"/>
    <p style="color: #FF0000">${passwordConfirmError}</p>
    <input type="submit" value="送信"/>
</form>
<br />
<br />
<button onclick="location.href='/user'">戻る</button>
</body>
</html>