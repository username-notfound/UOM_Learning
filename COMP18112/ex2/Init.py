import im
import time

server= im.IMServerProxy('http://webdev.cs.manchester.ac.uk/~u15794sg/IMServer.php')
server.clear()
server['user1Status'] = 'Unoccupied'
server['user2Status'] = 'Unoccupied'
