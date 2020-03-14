#include "common.h"

void INT_ALL(void)
{
	USART_Config(); 	//��ʼ������1   PC���� 
	bc20_GPIO_USART_init();
	B20_GNSS_INIT();//GNSS��ʼ��
	if(bc20_Start_int())printf ( "\r\n BC20 ���� �ɹ������� .\r\n" );
	else printf ( "\r\n BC20 ����ʧ��..\r\n" );
	while(DHT11_Init()){}
	MQ4_ADC_init();
	printf ( "\r\n ���г�ʼ�����\r\n" );
}

DataObj getAllDataOBJ(void)
{
	DataObj mData;
	mData.id=USER_ID;
	mData.cName=USER_NAME;
	u8 temp=0,humi=0;
	while(DHT11_Read_Data(&temp,&humi));
	mData.hum=(float)humi;
	mData.temp=(float)temp;
/*	mData.CO2_CON=(double)10.12;
	mData.O2_CON=(double)21.011;*/
	mData.CH4_CON=(float)GetMQ4Value()/100;
	mData.Light_intensity=50;
	mData.location=getBC20_GNSSPWR_Address();
	while(!strcmp(mData.location,"0.0"))mData.location=getBC20_GNSSPWR_Address();
	return mData;
}

char *getJSON_Data(DataObj *dataObj)
{
	char *result;
	cJSON *root;	
	root=cJSON_CreateObject();
	cJSON *ArrayItem = cJSON_CreateObject();
	
	cJSON_AddNumberToObject(ArrayItem,"Temp",dataObj->temp);
	cJSON_AddNumberToObject(ArrayItem,"Humi",dataObj->hum);
//	cJSON_AddNumberToObject(ArrayItem,"O2",dataObj->O2_CON);
	//cJSON_AddNumberToObject(ArrayItem,"CO2",dataObj->CO2_CON);
	cJSON_AddStringToObject(ArrayItem,"Loca",dataObj->location);
	cJSON_AddNumberToObject(ArrayItem,"CH4",dataObj->CH4_CON);
	cJSON_AddNumberToObject(ArrayItem,"Light",dataObj->Light_intensity);
	
	cJSON_AddStringToObject(root,"USER_ID",(const char *)dataObj->id);
	cJSON_AddStringToObject(root,"USER_NAME",(const char *)dataObj->cName);
	cJSON_AddItemToObject(root,"DataObj",ArrayItem);
	result = cJSON_Print(root);
	cJSON_Delete(root);
	return result;
}

bool BC20HTTP_postBody(char *body)
{
	char *finalString=postString(body);
	printf("%s\n",finalString);
	
	while(!BC20_CMD("AT+QISTATE?","+QISTATE:",NULL,500))
		BC20_LINK_SERVER(enumTCP,BC20_LINK_SERVER_IP,BC20_LINK_SERVER_PORT);
//	return BC20_sendString("0123456789",10);
	while(!BC20_sendHex(finalString,strlen((const char*)finalString))){
	while(!BC20_CMD("AT+QISTATE?","+QISTATE:",NULL,500))
		BC20_LINK_SERVER(enumTCP,BC20_LINK_SERVER_IP,BC20_LINK_SERVER_PORT);
	}
	return true;
}

void BC20_TEST(void)
{
	INT_ALL();
	DataObj dataobj;
	char*res;
	if(BC20_LINK_SERVER(enumTCP,BC20_LINK_SERVER_IP,BC20_LINK_SERVER_PORT))
	{
		printf("���ӳɹ�");
	}	
	if(BC20_CMD("AT+QISTATE?","+QISTATE:",NULL,500))
	{
		printf("״̬��ѯ�ɹ�");
		if(BC20_CloseSingle(0)) printf("�رճɹ�");
	}else printf("״̬��ѯʧ��");
	
	while(1)
	{
		dataobj=getAllDataOBJ();
		res=getJSON_Data(&dataobj);
		BC20HTTP_postBody(res);
		free(res);
		res=NULL;
		delay_ms(20000);	
	}
}


