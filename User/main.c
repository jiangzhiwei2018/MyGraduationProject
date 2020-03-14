#include "stm32f10x.h"
#include "usart.h"
#include "delay.h"
#include "common.h"

 int main ( void )
{
	/* BC20*/
	BC20_TEST();
	//My_ESP_8266_Test();
//	USART_Config(); 
	//MQ4_ADC_init();
  while (1)
	{
		//printf("ÊýÖµÎª%d",GetMQ4Value());
		//delay_ms(3000);
	}
}
