#include <cstdio>
#include <cstdint>

// 定义数据包的最大长度
#define MAX_PACKET_LENGTH 256

// 定义包头
const uint8_t header[2] = {0xaa, 0x55};

// 生成校验字节的函数
uint8_t calculateChecksum(const uint8_t *data, size_t length) {
    uint8_t checksum = 0;
    for (size_t i = 0; i < length; i++) {
        checksum += data[i];
    }
    return checksum;
}

void generateInstruction(){
    // 命令字
    uint8_t command = 0x16;

    // 数据
    uint8_t data[] = {0x02,};
    size_t dataLength = sizeof(data) / sizeof(data[0]);

    // 计算包长
    uint8_t packetLength = sizeof(command) + dataLength + 1; // 命令字长度 + 数据长度 + 校验字节长度

    // 创建数据包
    uint8_t packet[MAX_PACKET_LENGTH];
    size_t index = 0;

    // 添加包头
    packet[index++] = header[0];
    packet[index++] = header[1];

    // 添加包长
    packet[index++] = packetLength;

    // 添加命令字
    packet[index++] = command;

    // 添加数据
    for (size_t i = 0; i < dataLength; i++) {
        packet[index++] = data[i];
    }

    // 计算并添加校验字节
    uint8_t checksum = calculateChecksum(packet, packetLength + 2);
    packet[index++] = checksum;

    // 打印生成的数据包
    printf("Generated Packet: ");
    for (size_t i = 0; i < index; i++) {
        printf("%02X ", packet[i]);
    }
    printf("\n");

}

#include <iostream>
#include <cstdlib>
#include "window//SerialInterface.h"
#include <windows.h>
#include <tchar.h>

using namespace std;

int main()
{
    generateInstruction();
}

