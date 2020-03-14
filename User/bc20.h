#ifndef __BC20_H
#define	__BC20_H

#include "common.h"
#define BC20_CMD    ESP8266_Cmd





#define BC20_LINK_SERVER_IP 	"47.101.158.218"
#define BC20_LINK_SERVER_PORT  "80"
/***************************************************BC20函数表**********************************************************************/
void                   bc20_GPIO_USART_init          (void);
bool                   bc20_Start_int                (void);
bool                   bc20_AT_Test                  (void);
char                   getBC20_Signal_strength       (void);
char                   getBC20_Internet_status       (void);
char                  *getBC20_Vision                (void);
char                  *getBC20_SIM_ID                (void);
char                  *getBC20_GNSSPWR_Address       (void);
void                   B20_GNSS_INIT                 (void);
bool                   GNSS_Open                     (void);



/**********************************************带参函数表*************************************************************************/
bool 					BC20_sendString										(char * pStr,u32 ulStrLength);
bool 					BC20_sendHex											(char * pStr,u32 ulStrLength);
bool          BC20_CloseSingle                  (u8 connectID)               ;
#endif 


