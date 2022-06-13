JC = javac
R = java
JFlags = 
SUPER = src/
SUB1 = bst/
SUB2 = product/
SUB3 = structure/
SUB4 = user/
TEST = test/
DRIVER = Driver

muo:
	$(JC) $(JFlags) $(SUPER)$(SUB1)*.java $(SUPER)$(SUB2)*.java $(SUPER)$(SUB3)*.java $(SUPER)$(SUB4)*.java $(SUPER)$(TEST)*.java -d bin
	$(R) -cp bin $(SUPER)$(TEST)$(DRIVER)

javadoc:
	javadoc $(SUPER)$(SUB1)*.java $(SUPER)$(SUB2)*.java $(SUPER)$(SUB3)*.java $(SUPER)$(SUB4)*.java $(SUPER)$(SUB5)*.java $(SUPER)$(TEST)*.java -d javadoc

clean:
	rm javadoc/ bin/ -r