#include "common.h"
#define RXBUF strEsp8266_Fram_Record .Data_RX_BUF
#define RxBuffer  RXBUF 
char *strx,*extstrx;
char *Getdata_Change(void);
struct 
{
char Latitude[10];//经度原数据
char longitude[9];//纬度源数据
char Latitudess[3];//整数部分
char longitudess[2];
char Latitudedd[7];//纬度小数点部分
char longitudedd[7];
float TrueLatitude;//转换过数据
float Truelongitude;//转换过数据	
char buffer[64];//存储转换的经纬度数据
char data_len[10];	
}LongLatidata;

void bc20_GPIO_USART_init(void)
{
	ESP8266_Init();
}

bool bc20_Start_int(void)
{
  printf ( "\r\n正在配置 BC20 ......\r\n" );
	return	
		GNSS_Open()&&
		bc20_AT_Test()&&
		BC20_CMD("ATI","OK",NULL,500)&&
		BC20_CMD("AT+CGSN=1","OK",NULL,500)&&
		BC20_CMD("AT+CIMI","OK",NULL,500)&&
		BC20_CMD("AT+CPIN?","OK",NULL,500)&&
		getBC20_Signal_strength()&&
		getBC20_Internet_status()
		;
}
bool bc20_AT_Test(void)
{
	char count=0;
	while ( count < 10 )
	{
		if( BC20_CMD("AT","OK",NULL,200))return true;
		++count;
		printf( "\r\n AT指令第%d次测试失败,正在进行第%d次重试..\r\n",count-1,count); 
	}
	printf( "\r\n AT测试失败!!\r\n" ); 
	return false;
}
char getBC20_Signal_strength(void)
{
	char *res,result;
	if(BC20_CMD("AT+CESQ","OK",NULL,500));
	res=strstr(RXBUF,": ")+2;
	result=(*res-'0')*10+(*(res+1)-'0');
	return result<99?result:0;
}
char getBC20_Internet_status(void)
{
	char *res;
	u8 count=0;
	while(!BC20_CMD("AT+CGATT?","OK",NULL,500) && count<=10)
	{
			BC20_CMD("AT+CGATT=1","OK",NULL,500);
			delay_ms(500);
			count++;
	}
	if(count>10) printf ( "\r\n  注网失败.\r\n" );
	
	res=strstr(RXBUF,": ")+2;
	return  *res-'0';
}

void B20_GNSS_INIT(void)
{
	GNSS_Open();
	printf ( "\r\n正在初始化GPS.\r\n" );
	while(!strcmp(getBC20_GNSSPWR_Address(),"0.0")){
		GNSS_Open();
		delay_ms(1000);
	}
	printf ( "\r\n正在初始化完毕.\r\n" );
}

char *getBC20_GNSSPWR_Address(void)
{
	BC20_CMD("AT+QGNSSRD=\"NMEA/RMC\"","OK",NULL,500);
	strx=strstr((const char*)RXBUF,(const char*)"+QGNSSRD:");
	 return Getdata_Change();
}

bool GNSS_Open(void)
{
	if(!BC20_CMD("AT+QGNSSC?","+QGNSSC: 1",NULL,500))
	{
		return BC20_CMD("AT+QGNSSC=1","OK",NULL,500);
	}
	return true;
}
char *Getdata_Change(void)
{
	unsigned char i;
	char *strx;
	char json[]="%d.%06d,%d.%06d";
		strx=strstr((const char*)RxBuffer,(const char*)"A,");//返回A，表明经纬度数据被正确获取了
		if(strx)
		{
			for(i=0;i<9;i++)
			LongLatidata.longitude[i]=strx[i+2];//纬度获取到3151.0237
		strx=strstr((const char*)RxBuffer,(const char*)"N,");//返回N，读取纬度数据11702.5641
		if(strx)
		{
			for(i=0;i<10;i++)
			LongLatidata.Latitude[i]=strx[i+2];//纬度数据获取到	
			for(i=0;i<3;i++)
			LongLatidata.Latitudess[i]=LongLatidata.Latitude[i];
			for(i=3;i<10;i++)
			LongLatidata.Latitudedd[i-3]=LongLatidata.Latitude[i];
			LongLatidata.Truelongitude=(LongLatidata.Latitudess[0]-0x30)*100+(LongLatidata.Latitudess[1]-0x30)*10+(LongLatidata.Latitudess[2]-0x30)\
			+((LongLatidata.Latitudedd[0]-0x30)*10+(LongLatidata.Latitudedd[1]-0x30)+(float)(LongLatidata.Latitudedd[3]-0x30)/10+\
			(float)(LongLatidata.Latitudedd[4]-0x30)/100+(float)(LongLatidata.Latitudedd[5]-0x30)/1000+(float)(LongLatidata.Latitudedd[6]-0x30)/10000)/60.0;//获取完整的数据
			//			///////////////////////////////////////////
			for(i=0;i<2;i++)
			LongLatidata.longitudess[i]=LongLatidata.longitude[i];
			for(i=2;i<9;i++)
			LongLatidata.longitudedd[i-2]=LongLatidata.longitude[i];	
			LongLatidata.TrueLatitude=(LongLatidata.longitudess[0]-0x30)*10+(LongLatidata.longitudess[1]-0x30)\
			+((LongLatidata.longitudedd[0]-0x30)*10+(LongLatidata.longitudedd[1]-0x30)+(float)(LongLatidata.longitudedd[3]-0x30)/10+\
			(float)(LongLatidata.longitudedd[4]-0x30)/100+(float)(LongLatidata.longitudedd[5]-0x30)/1000+(float)(LongLatidata.longitudedd[6]-0x30)/10000)/60.0;//获取完整的数据b
			
			#define lng   (unsigned int )LongLatidata.Truelongitude,(unsigned int)((LongLatidata.Truelongitude-(unsigned int )LongLatidata.Truelongitude)*1000000)
			#define lat   (unsigned int)LongLatidata.TrueLatitude,(unsigned int)((LongLatidata.TrueLatitude-(unsigned int )LongLatidata.TrueLatitude)*1000000)
			sprintf(LongLatidata.buffer,json,lat,lng);//浮点型数据转成字符串数据
			sprintf(LongLatidata.data_len,"%d",strlen(LongLatidata.buffer));//长度转成字符串
			return LongLatidata.buffer;
		}
		}
		else
		{
			return (char *)"0.0";
		}
		return (char *)"0.0";
}

bool BC20_LINK_SERVER(ENUM_NetPro_TypeDef enumE, char * ip, char * ComNum)
{
	char cStr [100] = { 0 }, cCmd [120];
	switch (enumE)
  {
		case enumTCP:
		  sprintf ( cStr,"1,0,\"%s\",\"%s\",%s,0,1", "TCP", ip, ComNum );
		  break;
		case enumUDP:
		  sprintf ( cStr,"1,0,\"%s\",\"%s\",%s,0,1", "UDP", ip, ComNum );
		  break;
		default:
			break;
  }
	sprintf( cCmd,"AT+QIOPEN=%s",cStr );
	return BC20_CMD(cCmd,"+QIOPEN: ",NULL,1000);
}

bool BC20_sendString(char * pStr,u32 ulStrLength)
{
	char headstr[32];
	sprintf(headstr,"AT+QISEND=0,%d,",ulStrLength);
	macESP8266_Usart("%s",headstr);
	
	return BC20_CMD(pStr,"SEND OK",NULL,1000);
}

char Value2Hex(const int value)
{
	char Hex = NULL;
	if (value>=0 && value<=9){
		Hex = (char)(value+'0');
	}
	else{
		if (value>9 && value<16){
			Hex = (char)(value-10+'A');
		}
		else{
			printf("the input value is out of range\n");
		}
	}
	return Hex;
}
int Str2Hex(char *str,char *hex)
{
	int high = 0;
	int low = 0;
	int temp = 0;

	if (NULL==str || NULL==hex){
		//printf("the str or hex is wrong\n");
		return -1;
	}

	if (0==strlen(str)){
		//printf("the input str is wrong\n");
		return -2;
	}

	while(*str)
	{
		temp = (int)(*str);
		high = temp>>4;
		low = temp & 15;
		*hex = Value2Hex(high);
		hex++;
		*hex = Value2Hex(low);
		hex++;
		str++;
	}
	*hex = '\0';
	return 0;
}

bool BC20_sendHex(char * pStr,u32 ulStrLength)
{
	char headstr[32];
	char hex[1024];
	sprintf(headstr,"AT+QISENDEX=0,%d,",ulStrLength);
	macESP8266_Usart("%s",headstr);
	Str2Hex(pStr,hex);
	return BC20_CMD(hex,"SEND OK",NULL,1000);
}

bool BC20_CloseSingle(u8 connectID)
{
	char cCmd[20];
	sprintf(cCmd,"AT+QICLOSE=%d",connectID);
	return BC20_CMD(cCmd,"CLOSE OK",NULL,1000);
}


/*********************以下两个方法暂有BUG不可用*******************************/
char *getBC20_SIM_ID(void)
{
	char result[16];
	char *res;
	if(BC20_CMD("AT+CIMI","OK",NULL,500));
	res=strstr(RXBUF,"CIMI")+4;
	u8 i=0;
	while(('0'<=*(res+i)&& *(res+i)<='9'))
	{
		result[i]=*(res+i);
		i++;
	}
	printf("SIM卡长度为 %d",i);
	result[i]='\0';
	res=result;
	return res;
}

char *getBC20_Vision(void)
{
	char result[16];
	char *res;
	if(ESP8266_Cmd("ATI","OK",NULL,500));
	res=strstr(RXBUF,"Revision: ")+10;
	u8 i=0;

	while(('0'<=*(res+i)&& *(res+i)<='9')||('A'<=*(res+i)&& *(res+i)<='Z'))
	{
		result[i]=*(res+i);
		i++;
	}
	
	result[i]='\0';
	printf("version长度为 %d",i);
	int j=0;
	for(j=0;result[i]!=0;j++){
		printf("%d",result[j]);
	}
	res=result;
	return res;
}



