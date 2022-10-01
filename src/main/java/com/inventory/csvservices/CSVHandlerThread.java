package com.inventory.csvservices;

import com.inventory.csvservices.*;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.stream.Collectors;

public class CSVHandlerThread implements Runnable {
	private static final String dirPath = "csv_files";
	private static final char csvSeparator = '|';
	private Map<String, FileTime> csvListWithTime;
	private List<String> updatedFiles=new ArrayList<>();

	@Override
	public void run() {
		csvListWithTime = new HashMap<>();
		
		// use executor service
		while ( true ) {
			try {
				
				searchCSVFiles();
				
				Thread.sleep(15 * 1000);
				
			} catch (InterruptedException e) {
				System.out.println("Interruption while sleeping!");
			} catch ( Exception e ) {
				System.out.println( "Exception thrown from searchCSVFiles !");
			}
		}
	}

	private void searchCSVFiles() throws Exception {
		try {
			File file = new File( dirPath );
			System.out.println(file.toString());
			List<String> updatedFiles = new ArrayList<>();
			String[] filenames = file.list();
			
			for(String x:filenames)
			{
				System.out.println(x);
			}
			System.out.println(file.toString());
			assert filenames != null;
			
			for ( String currFile : filenames ) {
				if ( currFile.endsWith(".csv") ) {
					if ( !csvListWithTime.containsKey(currFile) )  {
						csvListWithTime.put( currFile, null ); //why null
					}
					
					Path path = Paths.get(dirPath,currFile);
					BasicFileAttributes fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
					if ( csvListWithTime.get( currFile ) == null
							|| !csvListWithTime.get( currFile ).equals( fileAttributes.lastModifiedTime() ) ) { // formatting
						updatedFiles.add(currFile);
						csvListWithTime.put(currFile, fileAttributes.lastModifiedTime());
					}
				}
			}
			//this.updatedFiles = updatedFiles;
			if ( updatedFiles.size() > 0 ) {
				addUpdatedFilesData( updatedFiles);
			}
		} catch (IOException e ) {
			System.out.println("Problem in IO operations of file.. reading file attributes");
		} catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("Unexpected error occurred!");
		}
	}

	public void addUpdatedFilesData(List<String> updatedFiles ) {
		TShirtDAOHibernate tShirtDAOHibernate = null;
		for (String currCSVFile:updatedFiles) {
			try {
				List<TShirt> tshirtData = readCSVAddData(currCSVFile);
				tShirtDAOHibernate=new TShirtDAOHibernate();
				for (TShirt tshirtRow : tshirtData) {
                    tShirtDAOHibernate.insertTShirtDataInDB(tshirtRow);
                }
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}
	}

	public List<TShirt> readCSVAddData(String csvFile ) throws Exception {
		List<TShirt> tshirtData = new ArrayList<>();
		//Path path=Paths.get(dirPath,csvFile);
		csvFile = dirPath + "\\" + csvFile;

		try (FileReader fr = new FileReader(csvFile)) {

			 tshirtData = new CsvToBeanBuilder<TShirt>(fr)
					.withSkipLines(1)
					.withSeparator(csvSeparator)
					.withType(TShirt.class)
					.build()
					.stream()
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error While Reading!");
			e.printStackTrace();
		}
		return tshirtData;
	}


	
}