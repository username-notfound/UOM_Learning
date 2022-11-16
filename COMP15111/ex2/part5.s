; Age History

	B  main

born	DEFB "you were born in \0"
were	DEFB "you were \0"
in	DEFB " in \0"
are	DEFB "you are \0"
this	DEFB " this year\n\0"
	ALIGN

main

present DEFW	2005    ; int present = 2005
birth   DEFW    1959	; int birth = 1959
year    DEFW    0	; int year
age     DEFW    1	; int age = 1	
	
       	LDR R4, present
	LDR R5, birth
	LDR R6, year
	LDR R7, age
	
        ADR R0, born ; this code does System.out.println("you were born in " + birth);
	SVC 3
        MOV R0, R5 ; make sure this will work!
	SVC 4
	MOV R0, #10
	SVC 0
 
        
	ADD R6, R5, #1; year = birth + 1
        


loop	
        CMP R4, R6; while (year != present) {; this code does System.out.println("you were " + age + " in " + year);
	BEQ end
        ADR R0, were
	SVC 3
	MOV R0, R7 ; make sure this will work!
	SVC 4
	ADR R0, in
	SVC 3
	MOV R0, R6 ; make sure this will work!
	SVC 4
	MOV R0, #10
	SVC 0

	;   year = year + 1;
	ADD R6, R6, #1;
       
        ADD R7, R7, #1;   age = age + 1;
        
        BNE loop
	; }


	; this code does System.out.println("you are " + age + "this year");
        
end	ADR R0, are
	SVC 3
	MOV R0, R7 ; make sure this will work!
	SVC 4
	ADR R0, this 
	SVC 3
       

	SVC 2 ; stop

