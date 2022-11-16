#!/usr/bin/env python

import urllib

def function(website):
	data = urllib.urlopen(website)
	tokens = data.read().split()

	startPrinting = False
	startAdding = False

	forOrder = 0
	string = ""
	links = ""
	array = []
	noIn = [ '<em>', '<a']

	for token in tokens:
		if token == '</title>':
			startAdding = False
			startPrinting = True
			string += ""

		if token == '</h1>':
			startAdding = False
			startPrinting = True

		if token == '</p>':
			startAdding = False
			startPrinting = True

		if token == '</em>':
			startAdding = False
			string += "\b" + "*"

		if token == '</a>':
			startAdding = False
			startPrinting = True


		if startAdding == True and token not in noIn:
			string += token + " "

		if startPrinting == True:
			print string
			startPrinting = False
			string = ""

		if token == '</em>':
			startAdding = True

		if token == '<title>':
			startAdding = True
			startPrinting = False
			string += 'Page Title: '

		if token == '<h1>':
			startAdding = True
			startPrinting = False
			string += 'HEADING: '

		if token == '<p>':
			startAdding = True
			startPrinting = False
			string += '\nPARAGRAPH: '

		if token == '<em>':
			string += "*"

		if token == '<a':
			startAdding = False

		if token == '/a>':
			startAdding = True
			startPrinting = False

		if 'href="' in token:
			links += token
			links = links.replace('href="', "")
			links = links.replace('">', "")
			links = links.lstrip(".")
			array.append(links)
			links = ""
			startAdding = True

	for link in array:
		forOrder += 1
		print str(forOrder) + " : ." + link


	if array:
		myMessage = raw_input("Select a link: ")

		if (int(myMessage) > 0):
			function("http://studentnet.cs.manchester.ac.uk/ugt/COMP18112/"
			         + array[int(myMessage) - 1])



function("http://studentnet.cs.manchester.ac.uk/ugt/COMP18112/page3.html")
