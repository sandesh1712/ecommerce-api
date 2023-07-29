package helper;

import org.modelmapper.ModelMapper;

public class DTOHelper {
   private static ModelMapper mapper;
   
   private DTOHelper() {};
   
   static public ModelMapper getDTOmapper(){
	   if(mapper==null)
		   mapper= new ModelMapper();
	   
	   return mapper;
   }
}
