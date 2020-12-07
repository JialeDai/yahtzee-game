# yahtzee-game
## About the game
This is a dice game implemented by java using  swing and sockect. This is a relatively simple game to learn and involves 5 dice 1 player. In a round, the player takes a turn. On each turn, the player rolls the dice with to get them to one of the 13 categories listed. If the first roll doesn’t fulfill one of the categories, the player may choose to roll any or all of the dice again. If the second roll fails, the player may roll any or all of the dice again. By the end of the third roll, however, the player must assign the final dice configuration to one of the thirteen categories on the scorecard. If the dice configuration meets the criteria for that category, the player receives the appropriate score for that category; otherwise the score for that category is 0. Since there are thirteen categories and each category is used exactly once, a game consists of thirteen rounds. After the thirteenth round, all players will have received scores for all categories. The player with the total highest score is declared the winner. 
## Scoreing
The scoring is described here, based on the Yahtzee rules described in this website: [https://www.dicegamedepot.com/yahtzee-rules/](https://www.dicegamedepot.com/yahtzee-rules/) 
## GUI
Here's what the game interface looks like. \
![Screen Shot 2020-12-02 at 10.34.59 AM.png](https://i.loli.net/2020/12/08/KfbDO7zUNkuhwlq.png) \
The GUI is implemented by java swing.
## Features
### Saving Game
When the use finished the game, the program will give the final score and the user and choose to start a new game. But when the players so not finish the game, they can stop any time they like and store the game information to the database. The game uses Sqlite as its database and assumes the database is located on a remote server. To save the game , the game data and state will be marshalled into an Object and the object should be sent to the remote server. The server should receive the object and store the atat in a database table. \
![Picture1.png](https://i.loli.net/2020/12/08/Rc5iPvugA2VlxaT.png)
### Loading Game
To retrieve a saved game, the client should send a request to the server. The servers should send a list of games indexed by player name and time saved. When the user selects a game, it sends the request to a server, the server receives the requests, gets the data from a database, marshalls it into an object, and sends the object back to the client. \
![Picture1.png](https://i.loli.net/2020/12/08/HepVqtoZ56j9Wfn.png) \
The client then receives the object and adds its data to the game, and the player picks up where he left off.
