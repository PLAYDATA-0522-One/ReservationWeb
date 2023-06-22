<!DOCTYPE html>
<html>
<head>
    <title>Edit Page</title>
    <style>
        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .button {
            width: 150px;
            height: 40px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="button-container">
    <h1>Edit Page</h1>

    <div>
        <form method="get" action="/addAirplane">
            <input type="submit" class="button" value="Add Airplane">
        </form>
    </div>

    <div>
        <form method="get" action="/deleteAirplane">
            <input type="submit" class="button" value="Delete Airplane">
        </form>
    </div>

    <div>
        <form method="get" action="/deleteUser">
            <input type="submit" class="button" value="Delete User">
        </form>
    </div>

    <div>
        <form method="post" action="/editUser">
            <input type="submit" class="button" value="Edit User">
        </form>
    </div>
</div>
</body>
</html>
