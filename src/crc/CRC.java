package crc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//Created 10/12/16 by Scott Weagley for COMP 429 - Professor Isayan 

public class CRC {

    public static void main(String[] args) throws IOException{
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); 
        int data[], divisor[], crc[], verifyCrc[], crcResult[], crcDivisor[];
        int dataBits, divisorBits, crcBits, crcDivisorBits, menuChoice = 0;
        boolean validCrc = true, createdCRC = false;

        //Check user input and use appropriate function
        while(menuChoice != 3) {
            
            //Create a CRC code for user
            if(menuChoice == 1){
                System.out.println("Enter # of bits for data: ");
                dataBits = Integer.parseInt(userInput.readLine());
                data = new int[dataBits];

                System.out.println("Enter data in binary form: ");
                for(int i = 0; i < dataBits; i++){
                    data[i] = Integer.parseInt(userInput.readLine());
                }

                System.out.println("Enter # of bits for divisor: ");
                divisorBits = Integer.parseInt(userInput.readLine());
                divisor = new int[divisorBits];

                System.out.println("Enter divisor in binary form: ");
                for(int i = 0; i < divisorBits; i++){
                     divisor[i ]= Integer.parseInt(userInput.readLine());
                }

                //Initialize crc array
                crc = new int[dataBits + divisorBits - 1];
                for(int i = 0; i < data.length; i++ ){
                    crc[i] = data[i];
                }

                //Perform XOR division 
                int j = 0;
                int i = 0;

                while(j <= crc.length - divisor.length){            
                    while(i < divisor.length){
                        if(crc[j+i] == 1 && divisor[i] == 1){
                            crc[j+i] = 0;
                        } else {
                            crc[j+i] = crc[j+i] + divisor[i];
                        }  
                        i++;
                    }
                    i = 0;

                    while(j <= crc.length - divisor.length && crc[j]==0){
                        j++;
                    }
                }

                //Generate CRC code with appropriate remainder
                for(int k = 0; k < data.length; k++){
                    crc[k] = data[k];
                }

                createdCRC = true;
                System.out.println();
                menuChoice = 0;
                
            } else if(menuChoice == 2){
                
                //Verify CRC code if user has created one already
                if(createdCRC == true){
                    System.out.println("Enter # of bits for CRC code: ");
                    crcBits = Integer.parseInt(userInput.readLine());
                    verifyCrc = new int[crcBits];

                    System.out.println("Enter CRC code in binary form: ");
                    for(int i = 0; i < crcBits; i++){
                         verifyCrc[i]= Integer.parseInt(userInput.readLine());
                    }
                    
                    System.out.println("Enter # of bits for CRC divisor: ");
                    crcDivisorBits = Integer.parseInt(userInput.readLine());
                    crcDivisor = new int[crcDivisorBits];

                    System.out.println("Enter CRC divisor in binary form: ");
                    for(int i = 0; i < crcDivisorBits; i++){
                         crcDivisor[i]= Integer.parseInt(userInput.readLine());
                    }

                    //Initialize crcResult array
                    crcResult = new int[crcBits];
                    for(int i = 0; i < crcResult.length; i++ ){
                        crcResult[i] = verifyCrc[i];
                    }

                    //Perform XOR division 
                    int j = 0;
                    int i = 0;
                    
                    while(j <= crcResult.length - crcDivisor.length){            
                        while(i < crcDivisor.length){
                            if(crcResult[j+i] == 1 && crcDivisor[i] == 1){
                                crcResult[j+i] = 0;
                            } else {
                                crcResult[j+i] = crcResult[j+i] + crcDivisor[i];
                            }  
                            i++;
                        }
                        i = 0;

                        while(j <= crcResult.length - crcDivisor.length && crcResult[j]==0){
                            j++;
                        }
                    }
                    
                    for(int m = 0; m < crcResult.length; m++){
                        if(crcResult[m] != 0){
                            validCrc = false;                                                    
                        } else {
                            validCrc = true; 
                        }
                    }
                    
                    if(validCrc){
                        System.out.println("Valid CRC code. No data loss."); 
                    } else {
                        System.out.println("Error. CRC mismatch. Data loss.");
                    }                    

                    System.out.println();

                } else {
                    System.out.println("You must create a CRC code first!");
                }
                
                menuChoice = 0;  
               
            } else {
                //Print out menu
                System.out.println("Chose a menu item:");
                System.out.println("1.) Create CRC code to send to receiver.");
                System.out.println("2.) Verify if CRC code is error free.");
                System.out.println("3.) Quit program.");
                System.out.println();
                
                //Collect user input
                menuChoice = Integer.parseInt(userInput.readLine());
            }
        }
    }   
}


