import java.util.*;
import java.io.*;

public class Encoder
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String f = args[0];
        int k = Integer.parseInt(args[1]);

        int newRUN = 1;
	
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
        System.out.println(entropy);

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
                    newRUN = 0;
		    	}
                else if(newRUN == 1)
                {
                    file.delete();
                    newRUN = 0;
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


		input.close();
	}


}
 

