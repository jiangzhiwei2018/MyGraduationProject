#ifndef __COMMON_H
#define __COMMON_H



#include "stm32f10x.h"
#include "esp8266.h"
#include <stdio.h>  
#include <string.h> 
#include <stdbool.h>
#include "delay.h"
#include "usart.h"
#include "delay.h"
#include <stdarg.h>
#include "bc20.h"
#include "dht11.h"
#include "userfunc.h"
#include "cJSON.h"
#include "httpPost.h"
#include "MQ4.h"
//BC20指令模式 0为8266模式
#define MOD_BC20 0

/******************************* 宏定义 ***************************/
#define            macNVIC_PriorityGroup_x                     NVIC_PriorityGroup_2



/********************************** 函数声明 ***************************************/
void                     USART_printf                       ( USART_TypeDef * USARTx, char * Data, ... );



#endif /* __COMMON_H */

