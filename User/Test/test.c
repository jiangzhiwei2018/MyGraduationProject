//#include "test.h"
//#include "esp8266.h"
//#include <stdio.h>  
//#include <string.h>  
//#include <stdbool.h>



//volatile uint8_t ucTcpClosedFlag = 0;

//char cStr [ 1500 ] = { 0 };



///**
//  * @brief  ESP8266 （Sta Tcp Client）透传
//  * @param  无
//  * @retval 无
//  */
//void ESP8266_StaTcpClient_UnvarnishTest ( void )
//{
//	uint8_t ucStatus;
//	
//	char cStr [ 100 ] = { 0 };
//	
//		
//  printf ( "\r\n正在配置 ESP8266 ......\r\n" );

//	macESP8266_CH_ENABLE();
//	
//	ESP8266_AT_Test ();
//	
//	ESP8266_Net_Mode_Choose ( STA );

//  while ( ! ESP8266_JoinAP ( macUser_ESP8266_ApSsid, macUser_ESP8266_ApPwd ) );	
//	ESP8266_Cmd ( "AT+CIFSR", "OK", 0, 1000 );
//	ESP8266_Cmd ( "AT+CIPMUX=1", "OK", 0, 1000 );
//	
//	ESP8266_Cmd ( "AT+CIPSERVER=1,8288", "OK", 0, 1000 );
//	
//	printf ( "\r\n配置 ESP8266 完毕\r\n" );
//	
//		
//}


