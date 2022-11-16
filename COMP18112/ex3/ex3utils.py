"""

ex3utils.py - Module for ex3 - David Thorne / AIG / 15-01-2009

"""


import threading
import time
import socket as socketlib


class Socket():
	"""
	Mutable wrapper class for sockets.
	"""

	def __init__(self, socket):
		# Store internal socket pointer
		self._socket = socket
	
	def send(self, msg):
		# Ensure a single new-line after the message and encode.
		self._socket.send(("%s\n" % msg.strip()).encode('utf-8'))
		
	def close(self):
		self._socket.close()
		

class Receiver():
	"""
	A class for receiving newline delimited text commands on a socket.
	"""

	def __init__(self):
		# Protect access
		self._lock = threading.RLock()
		self._running = True

	def __call__(self, socket):
		"""Called for a connection."""
		# Set timeout on socket operations
		socket.settimeout(1)

		# Wrap socket for events
		wrappedSocket = Socket(socket)
		
		# Store the unprocessed data
		stored = b''
		chunk = b''
		
		# On connect!
		self._lock.acquire()
		self.onConnect(wrappedSocket)
		self._lock.release()
		
		# Loop so long as the receiver is still running
		while self.isRunning():
		
			# Take everything up to the first newline of the stored data
			(message, sep, rest) = stored.partition(b'\n')

			if sep == b'': # If no newline is found, store more data...
				while self.isRunning():
					try:
						chunk = b''
						chunk = socket.recv(1024)
						stored += chunk
						break
					except socketlib.timeout:
						pass
					except Exception as e:
						print('Exception caught while reading from socket: ' + str(e))
				
				# Empty chunk means disconnect
				if chunk == b'':
					break;

				# Don't pass the message on yet.
				continue
			else: # ...otherwise store the rest
				stored = rest			

			# Try to decode the message.
			try:
				message = message.decode('utf-8')
			except UnicodeDecodeError as e:
				print('Exception caught while decoding, Message dropped:\n    ' + str(e))
				message = ''

			# Messages should be delimited by a single line feed
			# character, but some clients (telnet) use DOS style,
			# and we're left with a trailing carriage return, which
			# confuses the students.
			# 
			# We can fix this by removing the last character of
			# 'message' iff it is \r.
			#
			# Testing against b'\r'[0] because a single index of a
			# byte string != a one character byte string, for some
			# reason.
			if message and message[-1] == b'\r'[0]:
				message = message[:-1]
				
			# Process the command
			if message:
				self._lock.acquire()
				success = self.onMessage(wrappedSocket, message)
				self._lock.release()

				if not success:
					break;

		# On disconnect!
		self._lock.acquire()
		self.onDisconnect(wrappedSocket)		
		self._lock.release()
		socket.close()
		del socket
		
		# On join!
		self.onJoin()
			
	def stop(self):
		"""Stop this receiver."""
		self._lock.acquire()
		self._running = False
		self._lock.release()
		
	def isRunning(self):
		"""Is this receiver still running?"""
		self._lock.acquire()
		running = self._running
		self._lock.release()
		return running
		
	def onConnect(self, socket):
		pass

	def onMessage(self, socket, message):
		pass

	def onDisconnect(self, socket):
		pass

	def onJoin(self):
		pass

		
		
class Server(Receiver):

	def start(self, ip, port):
		# Set up server socket
		serversocket = socketlib.socket(socketlib.AF_INET, socketlib.SOCK_STREAM)
		serversocket.setsockopt(socketlib.SOL_SOCKET, socketlib.SO_REUSEADDR, 1)
		serversocket.bind((ip, int(port)))
		serversocket.listen(10)
		serversocket.settimeout(1)
		
		# On start!
		self.onStart()

		# Main connection loop
		threads = []
		while self.isRunning():
			try:
				(socket, address) = serversocket.accept()
				thread = threading.Thread(target = self, args = (socket,))
				threads.append(thread)
				thread.start()
			except socketlib.timeout:
				pass
			except:
				self.stop()

		# Wait for all threads
		while len(threads):
			threads.pop().join()

		# On stop!				
		self.onStop()

	def onStart(self):
		pass

	def onStop(self):
		pass



class Client(Receiver):
	
	def start(self, ip, port):
		# Set up server socket
		self._socket = socketlib.socket(socketlib.AF_INET, socketlib.SOCK_STREAM)
		self._socket.settimeout(1)
		self._socket.connect((ip, int(port)))

		# On start!
		self.onStart()

		# Start listening for incoming messages
		self._thread = threading.Thread(target = self, args = (self._socket,))
		self._thread.start()
		
	def send(self, message):
		# Send message to server
		self._lock.acquire()
		self._socket.send(("%s\n" % message.strip()).encode('utf-8'))
		self._lock.release()
		time.sleep(0.5)

	def stop(self):
		# Stop event loop
		Receiver.stop(self)
		
		# Join thread
		if self._thread != threading.currentThread():
			self._thread.join()
		
		# On stop!
		self.onStop()		

	def onStart(self):
		pass

	def onStop(self):
		pass
		
	def onJoin(self):
		self.stop()
