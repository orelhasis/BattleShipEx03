<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>The Battle Ships Game</title>
    <jsp:include page="/Global/Header.jsp"></jsp:include>
    <script type="text/javascript" src="js/Lobby.js"></script>
    <link rel="stylesheet" type="text/css" href="css/Lobby.css">
</head>
<body>
<h1>BattleShips Lobby</h1>
<div>
    <h2>Welcome <%=session.getAttribute("PlayerName")%></h2> <div id="LogOutDiv">(<a href="Logout">Logout</a>)</div>
</div>
    <div class="center-div">
        <div>
            <div id="uploadGameDiv">
                <h3>Upload a new Game</h3>
                <form name="UploadGame" enctype="multipart/form-data" action="UploadGame" method="post" onsubmit="return submitForm();">
                    <table>
                        <tr><th>Game Name</th><td><input type="text" name="gameName"/></td></tr>
                        <tr><th>File</th><td><input type="file" name="gameFile"/></td></tr>
                        <tr><td colspan="2"><input type="button" name="SubmitGame" value="Upload Game"></td></tr>
                    </table>
                </form>
                <div id="Uploadoutput" style="display: none;"></div>
            </div>
            <div id="OnlinePlayers">
                <h3>Online Players</h3>
                <ul></ul>
            </div>
            <div id="availableGames">
                <jsp:include page="availableGames.jsp"/>
            </div>
        </div>
    </div>
</body>
</html>
