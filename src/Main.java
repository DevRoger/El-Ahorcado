/*****************************************************************************************************

 M03_REP_02: El Penjat

 Description: This game has to consist of choosing a word from 30 words that have been typed. The program has to show a
 mask with as many places as there are letters in the word you have to match. The user will be introducing letters
 and if they exist in the word, they will be placed in the corresponding place and if they do not, an error will
 occur.The game ends if all the letters of the word are correct or if 6 errors have been made.

 Autor: Roger Alonso

 ******************************************************************************************************/


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //Declarations
        int menu, random, lives;
        char letter;
        boolean find, eqquals;
        String[] words = {"Murcielago", "Elefante", "Cascabel", "Jirafa", "Margarita", "Leopardo", "Computadora", "Guitarra", "Camiseta", "Esmeralda", "Biblioteca", "Tigre", "Mariposa", "Piramide", "Fresadora", "Chocolate", "Pastel", "Perro", "Montana", "Astronauta", "Camioneta", "Serpiente", "Maquina", "Maraton", "Televisor", "Abogado", "Diamante", "Helado", "Ventana", "Mapache"};
        String incorrects;


        //MAIN
        do {
            menu = menu();

            switch (menu) {
                //Start the game
                case 1:

                    //Pick a random word and add it to the 2 arrays.
                    random = (int) (Math.random() * 29);
                    char[] arrayPalabra = words[random].toCharArray();

                    for (int i = 0; i < arrayPalabra.length; i++){
                        arrayPalabra[i] = Character.toUpperCase(arrayPalabra[i]);
                    }

                    char[] arrayResultado = arrayVoid(words[random]);

                    //Print of the mask
                    System.out.println(arrayResultado);

                    //Start the game
                    eqquals = false;
                    lives = 6;
                    incorrects = "";
                    while (lives != 0 && !eqquals){

                        //Used letters
                        usedLetters(incorrects);

                        //Get letter
                        letter = letter();

                        //Change letter in arrayResultado if exist
                        find = changeLetter(arrayPalabra, arrayResultado, letter);

                        //Check if the letter has been found
                        if (find) {
                            System.out.println("\nCorrect letter!");
                        } else {
                            System.out.print("\nIncorrect letter!");


                            //It takes away your life if the letter is incorrect
                            lives = substractLife(incorrects, letter, lives);

                            //Check if letter is in the String incorrects
                            incorrects = incorrectsString(incorrects, letter);


                            System.out.println(" " + lives + " lives left.");
                            draw(lives);
                        }

                        //Show mask with found letters
                        System.out.println(arrayResultado);

                        //Check if the word is found
                        eqquals = Arrays.equals(arrayPalabra, arrayResultado);

                    }

                    //Once the game is over, check if you win, or you lose
                    if (eqquals) {
                        System.out.println("Congratulations you won! " + lives + " lives left.");
                    } else {
                        System.out.println("Sorry, you lose!");
                        System.out.print("The result was  ");
                        for (char letra:arrayPalabra) {
                            System.out.print(letra);
                        }
                        System.out.println();
                    }

                    break;

                //Exit game
                case 2:

                    break;

                //Incorrect number
                default:
                    System.out.println("Incorrect number.");

            }

        } while (menu != 2);
    }
    private static int menu() {
        //This function show the menu and return te choosen option by the user
        int menu;

        System.out.println("\n      Menu");
        System.out.println("===============");
        System.out.println("1.- Start game");
        System.out.println("2.- Exit");

        menu = Teclat.llegirInt();

        return menu;
    }
    private static char[] arrayVoid(String word) {
        //Return a void array with the size of the word
        int size = word.length();
        char[] result = new char[size];
        Arrays.fill(result, '_');
        return result;
    }
    private static char letter() {
        //Ask and return a letter entered by the user
        char letter;
        System.out.println("\nTell me a letter: ");
        letter = Character.toUpperCase(Teclat.llegirChar());

        return letter;
    }
    private static void usedLetters(String incorrects){
        //Displays all letters entered by the user that are incorrect.
        System.out.println("\nIncorrect letters: ");
        for (int i = 0; i < incorrects.length(); i++) {
            char wrongLetter = incorrects.charAt(i);
            System.out.print(wrongLetter);

            if (i < incorrects.length()-1){
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    private static boolean changeLetter(char[] arrayPalabra, char[] arrayResultado, char letter){
        //Replaces the letter in arrayResultado if it is found in arrayPalabra and returns a boolean
        boolean found = false;
        for (int i = 0; i < arrayPalabra.length; i++) {
            if (arrayPalabra[i] == letter) {
                arrayResultado[i] = Character.toUpperCase(letter);
                found = true;
            }
        }
        return found;
    }
    private static void draw(int lives){
        //Draw the hanged man according to the number of lives you have left.
        switch (lives){
            case 0:
                System.out.println("-------------\n|           |\n|           o\n|          /|)\n|          / \\\n|\n|\n-----");
                break;
            case 1:
                System.out.println("-------------\n|           |\n|           o\n|          /|)\n|\n|\n|\n-----");
                break;
            case 2:
                System.out.println("-------------\n|           |\n|           o\n|\n|\n|\n|\n-----");
                break;
            case 3:
                System.out.println("-------------\n|\n|\n|\n|\n|\n|\n-----");
                break;
            case 4:
                System.out.println("\n|\n|\n|\n|\n|\n|\n-----");
                break;
            case 5:
                System.out.println("\n\n\n\n\n\n-----");
                break;

        }
    }
    private static String incorrectsString(String incorrects, char letter){
        //Returns a String of the wrong letters without repeating them
        boolean found = false;

        for (int i = 0; i < incorrects.length(); i++){
            if (incorrects.charAt(i) == letter) {
                found = true;
                break;
            }
        }

        if (!found) {
            incorrects += letter;
        }

        return incorrects;
    }
    private static int substractLife(String incorrects, char letter, int lives){
        //Subtracts life if the entered letter is incorrect
        boolean encontrar = false;

        for (int i = 0; i < incorrects.length(); i++){
            if (incorrects.charAt(i) == letter){
                encontrar = true;
                break;
            }
        }

        if (!encontrar) {
            lives--;
        }

        return lives;
    }
}