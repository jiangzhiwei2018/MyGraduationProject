
#include "common.h"
#include "esp8266.h"

char *postString(char*content_body)
{
	char dataBuff[POST_BUFF_SIZE];
	u32  len;
	char *stringlen;
	memset(dataBuff,0,POST_BUFF_SIZE);
	stringlen=(char *)malloc(128);
	memset(stringlen,0,128);
	len=strlen(content_body);
	sprintf(stringlen,"%d",len);
	strcat(dataBuff,"POST /MyProject3/PostData HTTP/1.1\n");
	strcat(dataBuff,"Host: 47.101.158.218:80\n");
	strcat(dataBuff, "Content-Type: application/json\n");
	strcat(dataBuff, "Content-Length: ");
	strcat(dataBuff,stringlen);
	strcat(dataBuff,"\n\n");
	strcat(dataBuff,content_body);
	strcat(dataBuff,"\r\n\r\n");
	free(stringlen);
	char * re=dataBuff;
	return  re;
}
bool Esp8266_postBody(char *body)
{
	printf("%s\n",body);
	/*ESP8266_Link_Server(enumTCP,IP,PORT,Single_ID_0); 
	ESP8266_SendString(DISABLE,body,strlen(body),Single_ID_0);
	ESP8266_ECloseTCP_UDP();*/
	return true;
}

