<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>ユーザー一覧</title>
</head>
<body>
<button onclick="location.href='/user/add'">新規登録</button>
<br />
<br />
<table>
    <tr>
        <th>ID</th>
        <th>ユーザー名</th>
        <th>パスワード</th>
    </tr>
<#list users as user>
<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.password}</td>
    <td><button onclick="location.href='/user/delete/${user.id}'">削除</button></td>
</tr>
<#else>
<tr>
    <td colspan="4">登録ユーザーなし</td>
</tr>
</#list>
</table>
</body>
</html>