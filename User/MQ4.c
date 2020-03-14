#include "MQ4.h"
void MQ4_ADC_init(void)
{
	GPIO_InitTypeDef GPIO_InitStructure;
	ADC_InitTypeDef ADC_InitStructure;
 
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA|RCC_APB2Periph_AFIO|RCC_APB2Periph_ADC2,ENABLE);
 
	RCC_ADCCLKConfig(RCC_PCLK2_Div6);//12M  ���14M ����ADCʱ�ӣ�ADCCLK��
	ADC_DeInit(ADC2);
 
	GPIO_InitStructure.GPIO_Pin=GPIO_Pin_1;//ADC
	GPIO_InitStructure.GPIO_Mode=GPIO_Mode_AIN;	//ģ������
	GPIO_InitStructure.GPIO_Speed=GPIO_Speed_50MHz;
	GPIO_Init(GPIOA,&GPIO_InitStructure);

	ADC_InitStructure.ADC_Mode = ADC_Mode_Independent; 
	ADC_InitStructure.ADC_ScanConvMode = DISABLE; 
	ADC_InitStructure.ADC_ContinuousConvMode = DISABLE; 
	ADC_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None; 
	ADC_InitStructure.ADC_DataAlign = ADC_DataAlign_Right; 
	ADC_InitStructure.ADC_NbrOfChannel = 1; 
	ADC_Init(ADC2, &ADC_InitStructure);
	
	//����ָ��ADC�Ĺ�����ͨ�����������ǵ�ת��˳��Ͳ���ʱ��
//	ADC_RegularChannelConfig(ADC1,ADC_Channel_1,1,ADC_SampleTime_239Cycles5);
	
	//�ڲ��¶ȴ���������ADC1ͨ��16�ġ�
	ADC_RegularChannelConfig(ADC2,ADC_Channel_1,1,ADC_SampleTime_239Cycles5);
//	ADC_TempSensorVrefintCmd(ENABLE);//���ڲ��¶ȴ�����ʹ��
	ADC_Cmd(ADC2,ENABLE);	
 
	ADC_ResetCalibration(ADC2);//����ָ����ADC��У׼�Ĵ���
	while(ADC_GetResetCalibrationStatus(ADC2));//��ȡADC����У׼�Ĵ�����״̬
	
	ADC_StartCalibration(ADC2);//��ʼָ��ADC��У׼״̬
	while(ADC_GetCalibrationStatus(ADC2));//��ȡָ��ADC��У׼����
 
	ADC_SoftwareStartConvCmd(ADC2, ENABLE);//ʹ�ܻ���ʧ��ָ����ADC�����ת����������
}
u16 GetMQ4Value(void)
{
			uint16_t ADCVal;
			float Voltage;
			uint16_t ppm;
			ADC_SoftwareStartConvCmd(ADC2, ENABLE);
	    while(!ADC_GetFlagStatus(ADC2, ADC_FLAG_EOC ));
			ADCVal = ADC_GetConversionValue(ADC2);
			Voltage = ADCVal * 3.3 / 4096;
	//����Ȼ���Ļ����£�ʵ��AOUT�˵ĵ�ѹΪ0.5V������⵽��Ȼ��ʱ����ѹÿ����0.1V,ʵ�ʱ�������Ũ������200ppm
			ppm = (Voltage - 0.5) / 0.1 * 200;
	printf("��ѹֵΪ:%lfV\n",Voltage);
			return ppm;
			//return ADCVal;
}

int Read_MQ4_DOUT_Data(void)
{
	int status;
	status = GPIO_ReadInputDataBit(GPIOC, GPIO_Pin_13);
	return status;
}


