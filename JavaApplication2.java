/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author Chen
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static char romaValue[] = {'I','V','X','L','C','D','M'};// Roma Value
    public static int[] number = {1,5,10,50,100,500,1000}; //Roma Value by number
    public static String[][] intergalatic = new String[0][2];//intergalatic value
    public static String[] other = new String[0];// other than intergalatic
    public static int[] otherValue = new int[0];// other value
    public static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        // TODO code application logic here
       init();//initial or begining of the system
    }
    //initial
    public static void init(){
        String result = "";
        String input;
        String askAgain;
        int creditTemp = 0;
        do{
            result="";
            System.out.print("Please input your statement/question : ");
            input = in.nextLine();
            if(input.indexOf("is")>0 && input.indexOf("?")>0 && intergalatic.length>0){
               if(input.indexOf("Credits")>0){
                   if(otherValue.length == 0){
                       result = "I have no idea what you are talking about\n";
                   }
                   else{
                       if(getCredits(input.substring(input.indexOf("is")+2,input.length()-1), creditTemp)==0){
                           result = "I have no idea what you are talking about\n";
                       }else{
                           result = result+input.substring(input.indexOf("is")+3,input.length()-2)+" is "+getCredits(input.substring(input.indexOf("is")+2,input.length()-1), creditTemp)+ " Credits\n";
                       }
                   }
               }
               else{
                   result = result+input.substring(input.indexOf("is")+3,input.length()-2)+" is "+getCredits(input.substring(input.indexOf("is")+2,input.length()-1), -1)+"\n";
               }
            }
            else if(input.indexOf("is")>0 && input.indexOf("?")<0){
               if(input.indexOf("Credits")>0){
                   if(intergalatic.length>0){
                       creditTemp = Integer.parseInt(input.substring(input.indexOf(" is")+4,input.indexOf("Credits")-1));
                       if(getCredits(input.substring(0,input.indexOf(" is")), creditTemp)==0){
                           result = "I have no idea what you are talking about\n";
                       }
                       else{
                           otherValue = Arrays.copyOf(otherValue, otherValue.length + 1);
                           otherValue[otherValue.length-1] = getCredits(input.substring(0,input.indexOf(" is")), creditTemp);
                       }
                   }
                   else{
                       result = "I have no idea what you are talking about\n";
                   }
               }
               else{
                   insertIntergalatic(input);
               }
               
            }
            else{
                result = "I have no idea what you are talking about\n";
            }
            
            System.out.print(result);
            
            do{
                System.out.print("Still wanna ask or givestatement[Yes/No] ? ");
                askAgain = in.nextLine();
            }while(askAgain.compareToIgnoreCase("yes")!=0 && askAgain.compareToIgnoreCase("no")!=0);
            creditTemp = 0;
        }while(askAgain.compareToIgnoreCase("yes")==0);
    }
    //changing roma value to number
    public static int romaToNumber(String roma){
        int[] temp =  new int[roma.length()];
        int total = 0;
        for(int i = 0; i<roma.length(); i++){
            for(int j = 0; j<romaValue.length;j++){
                if(roma.charAt(i)==romaValue[j]){
                    temp[i] = number[j];
                }
            }
        }
        for(int i = 0; i<temp.length;i++){
            if(i+1 == temp.length){
                total = total + temp[i]; 
            }
            else if(temp[i+1]>temp[i]){
                total = total - temp[i];
            }
            else{
                total = total + temp[i]; 
            }
        }
        return total;
    }
    //insert intergalatic value
    public static void insertIntergalatic(String input){
        String[] arrInter = input.split(" is ");
        boolean addInter = true;
        String[][] temp = new String[intergalatic.length+1][2];
        if(intergalatic.length>0){
            for(int i=0; i<intergalatic.length;i++){
                temp[i][0] = intergalatic[i][0];
                temp[i][1] = intergalatic[i][1];
                if(arrInter[0].compareToIgnoreCase(intergalatic[i][0])==0){
                    addInter = false;
                    break;
                }
            }
            if(addInter == true){
                intergalatic = temp;
                intergalatic[intergalatic.length-1][0] = arrInter[0];
                intergalatic[intergalatic.length-1][1] = arrInter[1];
            }
        }
        else{
            intergalatic = new String[intergalatic.length+1][2];
            intergalatic[intergalatic.length-1][0] = arrInter[0];
            intergalatic[intergalatic.length-1][1] = arrInter[1];
        }
            
    }
    // convert intergalatic value to Roma value
    public static String interToRoma(String[] arrOtherInter){
        String temp = "";
        for(int i=0; i<arrOtherInter.length;i++){
            for(int j = 0; j<intergalatic.length;j++){
                if(arrOtherInter[i].compareToIgnoreCase(intergalatic[j][0])==0){
                    temp = temp +intergalatic[j][1];
                }
            }
        }
        return temp;
    }
    // calculate the credit of intergalatic value
    public static int getCredits(String otherIntergalatic, int credit){
        String[] arrOtherInter = otherIntergalatic.split(" ");
        int[] arrOtherInterValue = new int[0];
        boolean addOther = true;
        String tempOther = "";
        int add=0;
        int total = 0;
        for(int i = 0 ; i < arrOtherInter.length; i++){
            for(int j = 0; j<intergalatic.length;j++){
                if(arrOtherInter[i].compareToIgnoreCase(intergalatic[j][0])==0 || arrOtherInter[i].compareToIgnoreCase("")==0){
                    if(arrOtherInter[i].compareToIgnoreCase(intergalatic[j][0])==0){
                        arrOtherInterValue = Arrays.copyOf(arrOtherInterValue, arrOtherInterValue.length + 1);
                        arrOtherInterValue[arrOtherInterValue.length-1] = romaToNumber(intergalatic[j][1]);
                    }
                    addOther = false;
                    break;
                }else{
                    add++;
                }
            }
            if(addOther == true && add>0){
                if(other.length>0){
                    for(int k = 0; k<other.length;k++){
                        if(arrOtherInter[i].compareToIgnoreCase(other[k])==0){
                            addOther = false;
                            break;
                        }
                    }
                    if(addOther == true && credit>0){
                        other = Arrays.copyOf(other, other.length + 1);
                        other[other.length-1] = arrOtherInter[i];
                    }
                }
                else{
                    if(credit>0){
                        other = Arrays.copyOf(other, other.length + 1);
                        other[other.length-1] = arrOtherInter[i];
                    }
                }
                tempOther = arrOtherInter[i];
            }
            addOther = true;
        }
        if(credit>0){
            if(arrOtherInterValue.length<2){
                if(arrOtherInterValue.length == 0){
                    total = 0;
                }
                else{
                    total = (credit+romaToNumber(interToRoma(arrOtherInter)))/arrOtherInterValue[0];
                }
            }
            else{
                if(arrOtherInterValue[0]==arrOtherInterValue[1]){
                    total = (credit+romaToNumber(interToRoma(arrOtherInter)))/arrOtherInterValue[0];
                }
                else{
                    total = (credit+romaToNumber(interToRoma(arrOtherInter)))/2*arrOtherInterValue[0];
                }
            }
        }
        else{
            if(credit<0){
                total = romaToNumber(interToRoma(arrOtherInter));
            }
            else{
                for(int i=0;i<other.length;i++){
                    if(tempOther.compareToIgnoreCase(other[i])==0){
                        credit = otherValue[i];
                        break;
                    }
                    else{
                        credit = 0;
                    }
                }
                if(arrOtherInterValue.length<2){
                    if(arrOtherInterValue.length == 0){
                        total = 0;
                    }
                    else{
                        total = credit*arrOtherInterValue[0]-(romaToNumber(interToRoma(arrOtherInter)));
                    }
                }
                else{
                    if(arrOtherInterValue[0]==arrOtherInterValue[1] && credit>0){
                        total = credit*arrOtherInterValue[0]-(romaToNumber(interToRoma(arrOtherInter)));
                    }
                    else if(arrOtherInterValue[0]!=arrOtherInterValue[1] && credit>0){
                        total = credit*2*arrOtherInterValue[0]-(romaToNumber(interToRoma(arrOtherInter)));
                    }
                    else{
                        total = credit;
                    }
                }
            }
        }
        return total;
    }
    
}
