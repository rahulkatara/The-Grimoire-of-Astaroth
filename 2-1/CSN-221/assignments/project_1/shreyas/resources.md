# Resources

### General Notes
- Hardware accelerators vs GPUs
- CGRA
- FPGA
- TPU
- ASIC
- Dense Matrix Multiply
- Benefits of ASIC:
	- Low power consumption
	- Ultra-high computing power
	- Reduces AI development and deployment costs
### [A General Guide to Applying Machine Learning to Computer Architecture](https://pdfs.semanticscholar.org/e385/b179a48061b122789321e6b3760882c87787.pdf)
- How can we apply machine learning to improve computer processing speeds?
- What metrics do we focus on?
- General metrics: Instructions Per Cycle, Latency, Power Consumed
- For distributed datacenters: Response Time
- For Systems on chips: IPS / IPC 
- For GPUs: FLOPS (Floating Point Operations Per Second)
> What metrics that characterise the runtime behaviour of a system and its components are valuable to know?
	> eg for cache:useful to know access patterns and adapt the cache accordingly.

---

### [Machine Learning Hardware - Medium Blog](https://medium.com/x8-the-ai-community/machine-learning-hardware-1dac168423fd)
- Parallel processing cuts down on execution time
- ![Harvard Architecture](https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Harvard_architecture.svg/362px-Harvard_architecture.svg.png)
- How a simple CPU works:
	- fetch-decode-execute-write cycle
	- Load-Understand-Multiply-Store Product in a new Variable
	- This was for one multiply operation
- Matrix multiplication (and addition and inversion etc) will require several such operations
![Multiplication Diagrams](https://miro.medium.com/max/700/1*enrCWdEtOyO94l9XbESaAw.png)

- GPU will calculate all values at the same time. *Parallel Computation*.
- GPU -> array of CPUs
- Neurons: Multiply and Accumulation (MAC) Operations. Thousands of neurons making the same operations.
- ![CPU vs GPU](https://miro.medium.com/max/576/1*-WBgX2RsSiXREORrcyPtMQ.jpeg)
- ![Throughput of Multiple CPUs vs Multiple GPUs](https://miro.medium.com/max/636/1*vT6C_wzB4OJOEw2RaMVBVQ.jpeg)
- CPUs are better at handling single operations and GPUs are better at handling multiple operations.
- CPU less expensive than GPU
- ASICS (Application Specific Integrated Circuits)
- TPU

---

### [Cornell Slides](http://www.cs.cornell.edu/courses/cs6787/2017fa/Lecture11.pdf)
- What limits DL? 
- Compute bound limitations
- Matrix multiplication: O(n**2) space but O(n**3) computation

---

### [Karl Rupp 1](https://www.karlrupp.net/2016/08/flops-per-cycle-for-cpus-gpus-and-xeon-phis/)

--- 

### [Karl Rupp 2](https://www.karlrupp.net/2013/06/cpu-gpu-and-mic-hardware-characteristics-over-time/)

---

### [Dense Matrix Multiplication](https://www-users.cs.umn.edu/~karypis/parbook/Lectures/AG/chap8_slides.pdf)

---


### [Processors in General](https://www.elprocus.com/what-are-different-types-of-processors-applications-and-characteristics/#:~:text=There%20are%20five%20types%20of,Processor%2C%20DSP%20and%20Media%20Processor)

---


### [GFG Difference Between Normal and AI Processors](https://www.geeksforgeeks.org/difference-between-normal-processor-and-ai-processor/)

---  

### [Hardware For Machine Learning](https://algorithmia.com/blog/hardware-for-machine-learning)

---
 

### [A List of the Best Processors for AI Acceleration](https://iot.eetimes.com/a-list-of-the-best-processors-for-ai-acceleration/)

  ---

[https://www.eetimes.eu/top-10-processors-for-ai-acceleration-at-the-endpoint/](https://www.eetimes.eu/top-10-processors-for-ai-acceleration-at-the-endpoint/)

  ---

### [Intel AI Processor Basics Brief](https://www.intel.com/content/dam/www/public/us/en/documents/white-papers/ai-processor-basics-brief.pdf)

  ---

### [Wikipedia AI Accelerator](https://en.wikipedia.org/wiki/AI_accelerator)
- GPUs are multi core CPUs
- GPUs and FPGAs perform almost 10x better than regular CPUs.
- COPUs are general purpose while accelerators employ strategies  such as optimised memory use 
---  

[https://analyticsindiamag.com/the-different-types-of-hardware-ai-accelerators/](https://analyticsindiamag.com/the-different-types-of-hardware-ai-accelerators/)

 --- 

### [Google TPU Architecture](https://cloud.google.com/tpu/docs/system-architecture)

--- 

### [Graphcore How to Build a Processor for Machine Learning](https://www.graphcore.ai/posts/how-to-build-a-processor-for-machine-learning)

---

### [ Accelerating Machine Intelligence ~ Libby Kinsey](https://medium.com/project-juno/accelerating-machine-intelligence-254edf16d1cc)

---

### [Microsoft FGPA](https://www.wired.com/2016/09/microsoft-bets-future-chip-reprogram-fly/)

---

###[NVIDIA Volta](https://arxiv.org/pdf/1804.06826.pdf)
