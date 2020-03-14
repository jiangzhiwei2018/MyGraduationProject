#ifndef __USERFUNC_H
#define __USERFUNC_H 


void INT_ALL(void);
#define USER_ID    "001"
#define USER_NAME  "Test1"
typedef struct JsonTest{
    u8*	      id;
    u8*  	    cName;
		float     hum;
		float     temp;
	/*	double		CO2_CON;
		double    O2_CON;*/
		float  			CH4_CON;
		int 			Light_intensity;     
		char*		  location;
}DataObj;


DataObj 							getAllDataOBJ(void)                    ;
char 									*getJSON_Data(DataObj *dataObj)        ;
void                  BC20_TEST(void)												 ;
bool                  BC20HTTP_postBody(char *body)   			 ;

#endif

