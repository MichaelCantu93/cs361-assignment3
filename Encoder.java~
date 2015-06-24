import java.util.*;
import java.io.*;

public class Encoder
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String f = args[0];
        int k = Integer.parseInt(args[1]);

        int newRunTest = 1;
	    int newRunEnc = 1;
        int newRunDec = 1;

		File filename = new File(f);     //specify file input
		if (filename.length() == 0)        //check if no input
		{
			System.out.print("File is empty.");
			System.exit(0);
		}
		Scanner input = new Scanner(filename);      //make scanner for file
		String line = input.nextLine();
		
        int lineCount = 0;
		String text = "";

        int [] lines = new int[26];
        int sum = 0;
        while (input.hasNextLine() || !line.equals(""))      //iterate through file
		{
            for(int i = 0; i < Integer.parseInt(line); i++)
            {
			    text = text + Character.toString((char)(lineCount + 65));   
                sum++;            
            }
            lines[lineCount] = Integer.parseInt(line);

            if(input.hasNextLine())
            { 
                line = input.nextLine();          
            }
            else
            {
                line = "";
            }
            lineCount++;
		}

        double entropy = 0;
        double freq = 0;
        for(int i = 0; i < lineCount; i++)
        {
            freq = (double)lines[i] / (double)sum;
            entropy = entropy + freq * (Math.log(freq)/Math.log(2));
        }
        entropy = (-1)*entropy;

        //WAS MAIN CODE FROM HUFFMAN WE FOUND ONLINE, MOVED TO OUR MAIN

        // we will assume that all our characters will have
        // code less than 256, for simplicity
        int[] charFreqs = new int[256];
        // read each character and record the frequencies
        for (char c : text.toCharArray())
            charFreqs[c]++;
 
        // build tree
        HuffmanTree tree = HuffmanCode.buildTree(charFreqs);
 
        // print out results
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");

        HuffmanCode.printCodes(tree, new StringBuffer());
        //END OF HUFFMAN MAIN CODE FROM THE HUFFMAN WE FOUND ONLINE
        String [] encodings = HuffmanCode.getArray();

        try
		{
			File file = new File("testText.txt");
			if (!file.exists())
			{
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getName(), true);
			BufferedWriter bw = new BufferedWriter(fw);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

        Random rand = new Random();
        int n;
        int start = 0;
        int end = lines[0] - 1;
        int place = 0;

        for(int i = 0; i < k; i++)
        {
            n = rand.nextInt(sum);

            while(place < lineCount)
            {
                if(n >= start && n <= end)
                {   
                    break;
                }
                else
                {
                    start = start + lines[place];
                    end = end + lines[place+1];
                    place++;
                }
            }
            try
		    {
		    	File file = new File("testText.txt");
		    	if (!file.exists())
		    	{
		    		file.createNewFile();
                    newRunTest = 0;
		    	}
                else if(newRunTest == 1)
                {
                    file.delete();
                    newRunTest = 0;
                }
		    	
		    	FileWriter fw = new FileWriter(file.getName(), true);
		    	BufferedWriter bw = new BufferedWriter(fw);
		    	bw.write(Character.toString((char)(place + 65)) + "\n");
		    	bw.close();
		    } catch (IOException e)
		    {
			    e.printStackTrace();
		    }
            start = 0;
            end = lines[0] - 1;
            place = 0;
        }

        File testText = new File("testText.txt");     //specify file input
		if (testText.length() == 0)        //check if no input
		{
			System.out.print("File is empty.");
			System.exit(0);
		}
		Scanner input1 = new Scanner(testText);      //make scanner for file
		String line1 = input1.nextLine();
		
        double bitCount = 0;

        while (input1.hasNextLine() || !line1.equals(""))      //iterate through file
		{
            try
		    {
		    	File file1 = new File("testText.enc1");
		    	if (!file1.exists())
		    	{
		    		file1.createNewFile();
                    newRunEnc = 0;
		    	}
                else if(newRunEnc == 1)
                {
                    file1.delete();
                    newRunEnc = 0;
                }
		    	
		    	FileWriter fw1 = new FileWriter(file1.getName(), true);
		    	BufferedWriter bw1 = new BufferedWriter(fw1);
		    	bw1.write(encodings[Arrays.asList(encodings).indexOf(line1)+1] + "\n");
                bitCount = bitCount + encodings[Arrays.asList(encodings).indexOf(line1)+1].length();
		    	bw1.close();
		    } catch (IOException e)
		    {
			    e.printStackTrace();
		    }
            if(input1.hasNextLine())
            { 
                line1 = input1.nextLine();          
            }
            else
            {
                line1 = "";
            }
        }

        File testTextDec = new File("testText.enc1");     //specify file input
		if (testTextDec.length() == 0)        //check if no input
		{
			System.out.print("File is empty.");
			System.exit(0);
		}
		Scanner input2 = new Scanner(testTextDec);      //make scanner for file
		String line2 = input2.nextLine();
		
        while (input2.hasNextLine() || !line2.equals(""))      //iterate through file
		{
            try
		    {
		    	File file2 = new File("testText.dec1");
		    	if (!file2.exists())
		    	{
		    		file2.createNewFile();
                    newRunDec = 0;
		    	}
                else if(newRunDec == 1)
                {
                    file2.delete();
                    newRunDec = 0;
                }
		    	
		    	FileWriter fw2 = new FileWriter(file2.getName(), true);
		    	BufferedWriter bw2 = new BufferedWriter(fw2);
		    	bw2.write(encodings[Arrays.asList(encodings).indexOf(line2)-1] + "\n");
		    	bw2.close();
		    } catch (IOException e)
		    {
			    e.printStackTrace();
		    }
            if(input2.hasNextLine())
            { 
                line2 = input2.nextLine();          
            }
            else
            {
                line2 = "";
            }
        }

        double encEff = bitCount/(double)k;

        double perDiff = (Math.abs(entropy - encEff) / ((entropy + encEff)/2)) * 100; 

        System.out.println("Entropy is: " + entropy + "\n" + "Our encoding efficiency: " + encEff + "\n" + "Percentage Difference: " + perDiff);

		input.close();
        input1.close();
        input2.close();
	}


}
 

