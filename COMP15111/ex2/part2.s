; Hello Someone program - version 2

	B main

hello	DEFB	"Hello \0"
goodbye	DEFB	" and good-bye!\n\0"
	ALIGN

main	ADR	R0, hello	; System.out.print("Hello ");
	SVC 	3
next				; while (true) {

	SVC	1		; input a character to R0

loop	CMP R0, #10
        BNE skip        
        SVC     0
	ADR	R0, goodbye 	;   System.out.println(" and good-bye!");
        SVC	3
        SVC  	2		;   stop the program
        B loop

	                        ; }// translate to ARM code

skip	SVC	0		; output the character in R0
	B	next		; } //while
