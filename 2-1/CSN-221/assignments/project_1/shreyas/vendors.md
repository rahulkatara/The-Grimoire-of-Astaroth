# Types of Processors
--> Prioritising throughput over latency
- AI Accelerators
- TPU (Google)
- GPU 
- NPU 
- FPGA (Microsoft, Intel Altera)
- AI ASICs - Google, Facebook, Amazon, Google, Intel Xeon Phi, NVIDIA: Drive PX-Series, Qualcomm  Zeroth, Huawei Ascend 910

Other Ai Accelerators from other vendors ai t embedded and robotics? **What**


## Google TPU:
- [When to use TPUs](https://cloud.google.com/tpu/docs/tpus#when_to_use_tpus)
- Tensor Processing unit Chip had been specifically designed for TensorFLow Framework. (Framework for math in ml and nn)
- Cloud Computing Access
- Compared to a GPU, it is designed for a high volume of low precision computation with more input/output operations per joule. 
- [Architecture](https://cloud.google.com/tpu/docs/system-architecture)
- TPU v3: (only available in US though)
	- 16 GiB of HBM for each TPU Core * 8 cores = total 128 GiB TPU Memory
	- Two MXUs for each TPU Core
	- Up to 2048 total TPU Cores and 32TiB of Total Memory in a TPU Pod
	- Each TPU has a scalar, vector, and matrix unit.
	- The MXU provides the bulk of the computational power. 
	- Increase FLOPS per core and memory capacity???
- Edge TPU:
	- TPU that runs on the edge of the IoT Network
	- 4 TOPS (Trillion Operation per second) using 2 watts of power.

## Graphcore 

- Poplar Software Stack (idk how this is relevant) - 2016
- Colossus GC2 - 2018
- GC200 - July 2020
	- 59 Billion transistor, 823 square mm IC w/ 1472 cores and 900 Mbyte of local memories

## Huawei Ascend 910 
[Da Vinci Architecture Source](https://forum.huawei.com/enterprise/en/huawei-da-vinci-ai-chip-architecture/thread/616780-895)
| Parameter | Specifications |
|-|-|
| Architecture | Da Vinci |
| Performance | 256 TFLOPS @ FP16  <br> 512 TOPS @INT8 |
| Max Power | 310W |
| Process | N7+ |
[Some stats](http://www.hisilicon.com/en/Products/ProductList/Ascend#:~:text=The%20massive%20boost%20in%20power,of%20its%20high%20computing%20power.)
Da Vinci Architecture has the following features:
- Unified Architecture:
	- All-scenario AI series chips ranging from tens of milliwatts 
- Scalable Computing
	- 4096 MAC (Multiply and Accumulate Calculation) per core per clock cycle
	- Flexible multi-core stacking, scalable cube: 16x16xN, where N = 16/8/4/2/1
	- Tensor vector, and scalar calculation units
- Scalable Memory
	- 4 TBytes per second L2 Buffer (What does this mean?)
	- 1.2 TBytes per second HBM

[What should an AI processor have?](https://forum.huawei.com/enterprise/en/ai-chips-depend-on-architecture-innovation/thread/613226-895)
[DaVinci PPT](https://www.hotchips.org/hc31/HC31_1.11_Huawei.Davinci.HengLiao_v4.0.pdf)
## Intel Xeon

## NVIDIA Pascal Architecture
