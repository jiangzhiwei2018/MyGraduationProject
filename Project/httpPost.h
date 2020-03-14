#ifndef __HTTPOST_H
#define	__HTTPOST_H

#define POST_BUFF_SIZE 2048
#include <stdbool.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <float.h>
#include <limits.h>
#include <ctype.h>
#include "cJSON.h"
#include "common.h"
char *postString(char*content_body);
bool Esp8266_postBody(char *body);
typedef struct HttpClient
{
	char *head;
	char *body;
	
}HttpClientPost;

#endif

