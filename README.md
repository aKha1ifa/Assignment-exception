# Assignment-exception

This assignment requires you to write a program that can read and reorder containers in an ARXML file. ARXML stands for Autosar XML, which is a format used by AUTOSAR (AUTomotive Open System ARchitecture), a standardization initiative for the automotive industry.

## Task 

Your program should Reorder the containers alphabetically by their name sub-container “SHORT-NAME”.


## Requirements:

- The name of the arxml file shall be an argument which needs to passed through the command line.

- If the input file doesn't have an .arxml extension, user-defined exception 
called “NotValidAutosarFileException”.

- If the input file is empty, your program should throw a user-defined exception 
called “EmptyAutosarFileException”.

- The output file shall be named as the same of the input file concatenated with “_mod.arxml”
• e.g. if the input was named “Rte_Ecuc.arxml” then the output should be “Rte_Ecuc_mod.arxml”.

- If any requirement is missing or unclear, you should make reasonable assumptions and 
document them in your code.

## Files :

- **Main.java**: This is the class where you should implement your program logic.

- **r.arxml**: This is an empty ARXML file for testing the empty file case.

- **e.arxml**: This is a normal ARXML file for testing the normal case.

- **e.txt**: This is a Word document for testing the wrong extension case.

- **run.bat**: This is a batch file that runs your program with different input files for testing purposes.

## How to run:

To run your program with a single input file, use the following command:

`java Main  <your_file>`

where `<your_file>` is the name of the ARXML file you want to process. For example:

`java Main.java e.arxml`

This will create a new file called “a_mod.arxml” with the reordered containers.

To run your program with all the test files provided, use the following command:

`run.bat`

This will run your program with each of the test files and show the output on the console.
