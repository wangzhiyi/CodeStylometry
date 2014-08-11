import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class DatasetCreator 
{
	
	public static void mergeSameAuthors(String parentDir, String outputFolderName)
	{
		  File file = new File(parentDir);
		   String[] directories = file.list(new FilenameFilter() 
		   {
		     @Override
		     public boolean accept(File current, String name) 
		     {
		       return new File(current, name).isDirectory();
		     }
		   });
		   System.out.println(Arrays.toString(directories));
		   

		   for(int j=0; j< directories.length; j++)
		    {
				  File yearFile = new File(parentDir+"/"+directories[j]);

			   String[] authorDirectories = yearFile.list(new FilenameFilter() 
			   {
			     @Override
			     public boolean accept(File current, String name) 
			     {
			       return new File(current, name).isDirectory();
			     }
			   });			   
			   
			   for(int k=0; k< authorDirectories.length; k++)
			    { 		   
			   String author_cpp_dir = parentDir + directories[j] +"/"+authorDirectories[k] +"/";
			   System.out.println(author_cpp_dir);
			   List test_file_paths = Util.listCPPFiles(author_cpp_dir);
			   for(int i=0; i< test_file_paths.size(); i++)
			   {
					   String filePath = test_file_paths.get(i).toString();
					   
					   File srcFolder = new File(author_cpp_dir);
				    	File destFolderParent = new File("/Users/Aylin/Desktop/Drexel/2014/ARLInternship/SCAA_Datasets/"
					   +outputFolderName) ;
				      	File destFolder = new File(destFolderParent +"/"+ authorDirectories[k].toString()) ;
				    	if(!destFolder.exists())
				    	{
							///System.out.println(file.getAbsolutePath());
				    		destFolder.mkdirs();
						}
				    	//make sure source exists
				    	if(!srcFolder.exists())
				    	{
				           System.out.println("Directory does not exist.");
				           //just exit
				           System.exit(0);
				        }else
				        {
				 
				           try{
				        	Util.copyFolder(srcFolder,destFolder);
				           }catch(IOException e)
				           {
				        	e.printStackTrace();
				        	//error, just exit
				                System.exit(0);
				           }
				        }
				   }  
			   }}
		
	}
	 

		public static void copyAuthorsWithExactFileNumber(String test_cpp_dir, int fileCount){

	   File file = new File(test_cpp_dir);
	   String[] directories = file.list(new FilenameFilter() 
	   {
	     @Override
	     public boolean accept(File current, String name) 
	     {
	       return new File(current, name).isDirectory();
	     }
	   });
	   System.out.println(Arrays.toString(directories));
	   for(int j=0; j< directories.length; j++)
	    {
//		   System.out.println(directories[j].toString());
		   String author_cpp_dir = test_cpp_dir + directories[j] +"/";
//		   System.out.println(author_cpp_dir);
		   List test_file_paths = Util.listCPPFiles(author_cpp_dir);
		   for(int i=0; i< test_file_paths.size(); i++)
		   {
//				int testIDlength = test_file_paths.get(i).toString().length();   
			   //if the author has 6 cpp files
			//   int fileCount =6;
			   if(test_file_paths.size() == fileCount)
			   {
				   System.out.println(author_cpp_dir);

				   String filePath = test_file_paths.get(i).toString();
				   //one empty file in each folder, skip that
				   System.out.println(filePath);  
				   
				   File srcFolder = new File(author_cpp_dir);
			    	File destFolderParent = new File("/Users/Aylin/Desktop/Drexel/2014/ARLInternship/SCAA_Datasets/"
				   +fileCount+"FilesPerAuthor") ;
			      	File destFolder = new File(destFolderParent +"/"+ directories[j].toString()) ;
			    	if(!destFolder.exists())
			    	{
						///System.out.println(file.getAbsolutePath());
			    		destFolder.mkdirs();
					}
			    	//make sure source exists
			    	if(!srcFolder.exists())
			    	{
			           System.out.println("Directory does not exist.");
			           //just exit
			           System.exit(0);
			        }else
			        {
			 
			           try{
			        	Util.copyFolder(srcFolder,destFolder);
			           }catch(IOException e)
			           {
			        	e.printStackTrace();
			        	//error, just exit
			                System.exit(0);
			           }
			        }
			   }   
		   }	   
	    }
	}



		public static void main(String[] args) throws Exception, IOException, InterruptedException 
		{
		String test_cpp_dir = "/Users/Aylin/Desktop/Drexel/2014/ARLInternship/SCAA_Datasets/includingquals/OtherCPPCode/2014/";	
		int fileCount = 6;
//		copyAuthorsWithExactFileNumber(test_cpp_dir, fileCount);
		String parentDir = "/Users/Aylin/Desktop/Drexel/2014/ARLInternship/SCAA_Datasets/includingquals/OtherCPPCode/";	
		String outputFolderName ="mergedAuthors";
	//	mergeSameAuthors(parentDir, outputFolderName);
		
		String allYearsMergedAuthors="/Users/Aylin/Desktop/Drexel/2014/ARLInternship/SCAA_Datasets/mergedAuthors/";
		copyAuthorsWithExactFileNumber(allYearsMergedAuthors, 5);


	
		}
}
