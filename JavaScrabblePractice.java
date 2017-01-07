package javascrabblepractice;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class JavaScrabblePractice {

    public static void main(String[] args) {
        // TODO code application logic here
        JavaScrabblePractice  j = new JavaScrabblePractice();
        /*System.out.println(j.Scrabble("ladilmy", "daily")); 
        System.out.println(j.Scrabble("eerriin", "eerie")); 
        System.out.println(j.Scrabble("orrpgma", "program")); 
        System.out.println(j.Scrabble("orppgma", "program")); 
        System.out.println(j.Scrabble("pizza??", "pizzazz")); 
        System.out.println(j.Scrabble("piizza?", "pizzazz")); 
        System.out.println(j.Scrabble("a??????", "program")); 
        System.out.println(j.Scrabble("b??????", "program")); 
        System.out.println(longest("dcthoyueorza"));
        System.out.println(longest("uruqrnytrois"));
        System.out.println(longest("rryqeiaegicgeo??"));
        System.out.println(longest("udosjanyuiuebr??"));
        System.out.println(longest("vaakojeaietg????????"));
        System.out.println(highest("dcthoyueorza"));
        System.out.println(highest("uruqrnytrois"));
        System.out.println(highest("rryqeiaegicgeo??"));
        System.out.println(highest("udosjanyuiuebr??"));
        System.out.println(highest("vaakojeaietg????????"));*/
        System.out.println(highestOrder("iogsvooely"));// 44 ("oology")
        //System.out.println(highestOrder("seevurtfci"));// 52 ("service")
        //System.out.println(highestOrder("vepredequi"));// 78 ("reequip")
        //System.out.println(highestOrder("umnyeoumcp"));
        //System.out.println(highestOrder("orhvtudmcz"));
        //System.out.println(highestOrder("fyilnprtia"));
    }
    
    public boolean Scrabble(String set, String word){
        LinkedList letterSet = createList(set);
        /*Node currentTile = new Node();
        Node nodeOne;
        int added = 0;*/
        char temp;
        for(int i = 0; i<word.length(); i++){
            temp=word.charAt(i);
            if(!checkTiles(letterSet, temp)){
                return false;
            }
        }
    return true;
    }
    
    static int GetLetterVal(char x){
        if(x=='q'|x=='z')
            return 10;
        else if(x=='j'|x=='x')
            return 8;
        else if(x=='k')
            return 5;
        else if(x=='f'|x=='h'|x=='v'|x=='w'|x=='y')
            return 4;
        else if(x=='b'|x=='c'|x=='m'|x=='p')
            return 3;
        else if(x=='d'|x=='g')
            return 2;
        else if(x=='e'|x=='a'|x=='i'|x=='o'|x=='n'|x=='r'|x=='t'|x=='l'|x=='s'|x=='u')
            return 1;
        else
            return 0;
    }
    
    static boolean checkTiles(LinkedList LS, char t){
        Node tempNode;
        int found = 0;
        for(int c = 0; c<LS.size();c++){
            tempNode = (Node) LS.get(c);
            if(tempNode.letter == t){
                tempNode.uses++;
                found = 1;
                if(tempNode.uses > tempNode.quant)
                    if(!checkRando(LS))
                        return false;
                    else
                        tempNode.uses--;
            }
        }
        if(found == 0){
            if(!checkRando(LS))
                return false;
        }
        return true;
    }
    
    static boolean checkRando(LinkedList QuestList){
        Node tempNode;
        for(int q = 0; q < QuestList.size(); q++){
            tempNode = (Node) QuestList.get(q);
            if(tempNode.letter == '?' && tempNode.uses < tempNode.quant){
                tempNode.uses++;
                return true;
            }
        }
        return false;
    }
    
    public static String longest(String tiles){
        Scanner reader = null;
        String longest = "";
        File wordF = new File("C:\\Users\\John and Cass\\Documents\\NetBeansProjects\\JavaScrabblePractice\\enable1.txt");
        JavaScrabblePractice  j = new JavaScrabblePractice();
        try {
            //reader = new Scanner(new File("words.txt"));
            reader = new Scanner(wordF);
        } catch (java.io.FileNotFoundException e) {
        System.out.println("File not found!");
        }
        while(reader.hasNext()){
            String word = reader.nextLine();
            if(word.length()>longest.length()){
                if(j.Scrabble(tiles, word)) longest = word;
            }
        }
        return longest;
    }
    
    public static String highest(String tiles){ 
        Scanner reader = null;
        String highest = "";
        JavaScrabblePractice  j = new JavaScrabblePractice();
        int temp;
        int highscore = 0;
        try {
            reader = new Scanner(new File("C:\\Users\\John and Cass\\Documents\\NetBeansProjects\\JavaScrabblePractice\\enable1.txt"));        
        } catch (java.io.FileNotFoundException e) {
        System.out.println("File not found!");
        }
        while(reader.hasNext()){
            String word = reader.nextLine();
            if(j.Scrabble(tiles, word)){
                temp=j.calcScore(tiles, word);
                if(temp>=highscore){
                    highscore=temp;
                    highest=word;
                }
            }
        }
        return highest; 
    }
    
    public static String highestOrder(String tiles){ 
        Scanner reader = null;
        String highest = "";
        JavaScrabblePractice  j = new JavaScrabblePractice();
        int temp;
        int highscore = 0;
        try {
            reader = new Scanner(new File("C:\\Users\\John and Cass\\Documents\\NetBeansProjects\\JavaScrabblePractice\\enable1.txt"));        
        } catch (java.io.FileNotFoundException e) {
        System.out.println("File not found!");
        }
        while(reader.hasNext()){
            String word = reader.nextLine();
            if(j.Scrabble(tiles, word)){
                temp=j.calcScoreOrder(word);
                if(temp>=highscore){
                    highscore=temp;
                    System.out.println(highscore);
                    highest=word;
                    System.out.println(highest);
                }
            }
        }
        return highest; 
    }
    
    public int calcScore(String tiles, String word){
        int score = 0;
        char temp;
        LinkedList LS = createList(tiles);
        Node tempNode;
        for(int i = 0; i<word.length(); i++){
            temp=word.charAt(i);
            if(!checkTiles(LS, temp)){
                return 0;
            }
        }
        for(int j = 0; j < LS.size(); j++){
            tempNode = (Node) LS.get(j);
            score = score + (tempNode.val*tempNode.uses);
        }
        return score;
    }
    
    public int calcScoreOrder(String word){
        int score = 0;
        LinkedList LS = createOrderList(word);
        int mult = LS.size();
        Node tempNode;
        for(int j = 0; j < LS.size(); j++){
            tempNode = (Node) LS.get(j);
            score = score + (tempNode.val * mult);
            mult--;
        }
        return score;
    }
    
    public LinkedList createList(String setword){
        LinkedList LS = new LinkedList();
        Node currentTile = new Node();
        Node nodeOne;
        int added = 0;
        char temp;
        for(int j = 0; j<setword.length(); j++){
            temp=setword.charAt(j);
            if(LS.isEmpty()){
                currentTile.letter = temp;
                currentTile.quant = 1;
                currentTile.uses = 0;
                currentTile.val = GetLetterVal(temp);
                LS.push(currentTile);
            }
            else{
                for(int nodes = 0; nodes < LS.size(); nodes++){
                    nodeOne = (Node) LS.get(nodes);
                    if(nodeOne.letter == temp){
                        nodeOne.quant++;
                        added = 1;
                    }
                }
                if(added == 1)
                    added = 0;
                else{
                    currentTile = new Node();
                    currentTile.letter = temp;
                    currentTile.quant = 1;
                    currentTile.uses = 0;
                    currentTile.val = GetLetterVal(temp);
                    LS.push(currentTile);
                }
            }
        }
        return LS;
    }
    
    public LinkedList createOrderList(String setword){
        LinkedList LS = new LinkedList();
        Node currentTile;
        char temp;
        for(int j = 0; j<setword.length(); j++){
            currentTile = new Node();
            temp=setword.charAt(j);
            currentTile.letter = temp;
            currentTile.quant = 1;
            currentTile.uses = 0;
            currentTile.val = GetLetterVal(temp);
            LS.push(currentTile);
        }
        return LS;
    }
    
    public class Node{
        int quant = 0;
        char letter;
        int val;
        int uses;
    }
}


