A program that uses Linked Lists to simulate DNA strands and splicing new sequences into them. Simply an exercise
in the advantage of using an O(1) method over an O(n) method.

***INSTRUCTIONS***
WARNING: this program will run until your system's java allocated heap is consumed (doesn't take long).
Run DNABenchmark.
Prints out information about the DNA strand size and the time it takes to append new strands. 
***


Name: Alex Boldt
NetID: apb34
Hours Spent: 7 hours
Consulted With: Arun Ganesh's Review Session Video
Resources Used: Java 7/8 API  
Impressions: Pretty fun, it's satisfying to see how much time can be saved by using different implementations of code
to solve the same problem.
----------------------------------------------------------------------
PART 1
PROBLEM 1.a: 
CutAndSplice benchmark data from ecoli.txt:
Class	                splicee	      recomb	time
-----
SimpleStrand:	            256	      4,800,471	0.141	# append calls = 1290
SimpleStrand:	            512	      4,965,591	0.182	# append calls = 1290
SimpleStrand:	          1,024	      5,295,831	0.078	# append calls = 1290
SimpleStrand:	          2,048	      5,956,311	0.086	# append calls = 1290
SimpleStrand:	          4,096	      7,277,271	0.091	# append calls = 1290
SimpleStrand:	          8,192	      9,919,191	0.087	# append calls = 1290
SimpleStrand:	         16,384	     15,203,031	0.206	# append calls = 1290
SimpleStrand:	         32,768	     25,770,711	0.210	# append calls = 1290
SimpleStrand:	         65,536	     46,906,071	0.223	# append calls = 1290
SimpleStrand:	        131,072	     89,176,791	0.458	# append calls = 1290

I make the case that cutAndSplice is O(N) where N is the size of the final recomb strand because when the 
recomb size is 46,906,071 characters long, the runtime is 0.223, and when recomb size is 89,176,791 (about double
46 million), the runtime is 0.458, about double 0.223. This means that as the size of the recomb string is increasing,
the relationship between time and size is becoming closer to linear, so O(N). 

PROBLEM 1.b: 
Below is the data for the largest recomb strand string that fits in 512MB and 1024MB of memory:
					Class	                splicee	      recomb	time
512MB memory:		SimpleStrand:	      1,048,576	     47,505,810	0.358	
1024MB memory:		SimpleStrand:	      2,097,152	     94,691,730	0.833	

It can be seen that doubling the memory almost exactly doubled the final dna recomb string that fit in the memory
(so the next power-of-two string does fit).
The time approximately doubles as well, which supports the claim that cutAndSplice is O(N) where N is the final
recomb size.



PART 2
For this part, I made three new dna text files that are called ecoli_double.txt, ecoli_triple.txt, ecoliX5.txt,
and are 2, 3, and 5 times greater in length, respectively, than ecoli.txt. Therefore, they will have that factor
more breaks in their dna and I can use that to see if O(B) is true for LinkStrand. append calls = 2(breaks)
All data is below:

ecoli.txt:

Class	                splicee	      recomb	time
-----
LinkStrand:	            256	      4,800,471	0.174	# append calls = 1290
LinkStrand:	            512	      4,965,591	0.102	# append calls = 1290
LinkStrand:	          1,024	      5,295,831	0.095	# append calls = 1290
LinkStrand:	          2,048	      5,956,311	0.087	# append calls = 1290
LinkStrand:	          4,096	      7,277,271	0.091	# append calls = 1290
LinkStrand:	          8,192	      9,919,191	0.089	# append calls = 1290
LinkStrand:	         16,384	     15,203,031	0.107	# append calls = 1290
LinkStrand:	         32,768	     25,770,711	0.094	# append calls = 1290
LinkStrand:	         65,536	     46,906,071	0.098	# append calls = 1290
LinkStrand:	        131,072	     89,176,791	0.157	# append calls = 1290
LinkStrand:	        262,144	    173,718,231	0.082	# append calls = 1290
LinkStrand:	        524,288	    342,801,111	0.083	# append calls = 1290
LinkStrand:	      1,048,576	    680,966,871	0.081	# append calls = 1290
LinkStrand:	      2,097,152	  1,357,298,391	0.083	# append calls = 1290
LinkStrand:	      4,194,304	  2,709,961,431	0.082	# append calls = 1290
LinkStrand:	      8,388,608	  5,415,287,511	0.089	# append calls = 1290
LinkStrand:	     16,777,216	 10,825,939,671	0.081	# append calls = 1290
LinkStrand:	     33,554,432	 21,647,243,991	0.081	# append calls = 1290
LinkStrand:	     67,108,864	 43,289,852,631	0.092	# append calls = 1290

ecoli_double.txt:

Class	                splicee	      recomb	time
-----
LinkStrand:	            256	      9,600,942	0.227	# append calls = 2580
LinkStrand:	            512	      9,931,182	0.153	# append calls = 2580
LinkStrand:	          1,024	     10,591,662	0.165	# append calls = 2580
LinkStrand:	          2,048	     11,912,622	0.139	# append calls = 2580
LinkStrand:	          4,096	     14,554,542	0.147	# append calls = 2580
LinkStrand:	          8,192	     19,838,382	0.236	# append calls = 2580
LinkStrand:	         16,384	     30,406,062	0.244	# append calls = 2580
LinkStrand:	         32,768	     51,541,422	0.147	# append calls = 2580
LinkStrand:	         65,536	     93,812,142	0.150	# append calls = 2580
LinkStrand:	        131,072	    178,353,582	0.148	# append calls = 2580
LinkStrand:	        262,144	    347,436,462	0.155	# append calls = 2580
LinkStrand:	        524,288	    685,602,222	0.151	# append calls = 2580
LinkStrand:	      1,048,576	  1,361,933,742	0.149	# append calls = 2580
LinkStrand:	      2,097,152	  2,714,596,782	0.155	# append calls = 2580
LinkStrand:	      4,194,304	  5,419,922,862	0.150	# append calls = 2580
LinkStrand:	      8,388,608	 10,830,575,022	0.194	# append calls = 2580
LinkStrand:	     16,777,216	 21,651,879,342	0.218	# append calls = 2580
LinkStrand:	     33,554,432	 43,294,487,982	0.207	# append calls = 2580
LinkStrand:	     67,108,864	 86,579,705,262	0.174	# append calls = 2580

ecoli_triple.txt:

Class	                splicee	      recomb	time
-----
LinkStrand:	            256	     14,401,413	0.427	# append calls = 3870
LinkStrand:	            512	     14,896,773	0.198	# append calls = 3870
LinkStrand:	          1,024	     15,887,493	0.195	# append calls = 3870
LinkStrand:	          2,048	     17,868,933	0.209	# append calls = 3870
LinkStrand:	          4,096	     21,831,813	0.208	# append calls = 3870
LinkStrand:	          8,192	     29,757,573	0.273	# append calls = 3870
LinkStrand:	         16,384	     45,609,093	0.316	# append calls = 3870
LinkStrand:	         32,768	     77,312,133	0.271	# append calls = 3870
LinkStrand:	         65,536	    140,718,213	0.250	# append calls = 3870
LinkStrand:	        131,072	    267,530,373	0.366	# append calls = 3870
LinkStrand:	        262,144	    521,154,693	0.262	# append calls = 3870
LinkStrand:	        524,288	  1,028,403,333	0.250	# append calls = 3870
LinkStrand:	      1,048,576	  2,042,900,613	0.264	# append calls = 3870
LinkStrand:	      2,097,152	  4,071,895,173	0.256	# append calls = 3870
LinkStrand:	      4,194,304	  8,129,884,293	0.266	# append calls = 3870
LinkStrand:	      8,388,608	 16,245,862,533	0.261	# append calls = 3870
LinkStrand:	     16,777,216	 32,477,819,013	0.242	# append calls = 3870
LinkStrand:	     33,554,432	 64,941,731,973	0.251	# append calls = 3870
LinkStrand:	     67,108,864	129,869,557,893	0.249	# append calls = 3870

ecoliX5.txt:

Class	                splicee	      recomb	time
-----
LinkStrand:	            256	     24,002,355	0.460	# append calls = 6450
LinkStrand:	            512	     24,827,955	0.462	# append calls = 6450
LinkStrand:	          1,024	     26,479,155	0.664	# append calls = 6450
LinkStrand:	          2,048	     29,781,555	0.527	# append calls = 6450
LinkStrand:	          4,096	     36,386,355	0.531	# append calls = 6450
LinkStrand:	          8,192	     49,595,955	0.548	# append calls = 6450
LinkStrand:	         16,384	     76,015,155	0.481	# append calls = 6450
LinkStrand:	         32,768	    128,853,555	0.487	# append calls = 6450
LinkStrand:	         65,536	    234,530,355	0.461	# append calls = 6450
LinkStrand:	        131,072	    445,883,955	0.447	# append calls = 6450
LinkStrand:	        262,144	    868,591,155	0.445	# append calls = 6450
LinkStrand:	        524,288	  1,714,005,555	0.435	# append calls = 6450
LinkStrand:	      1,048,576	  3,404,834,355	0.439	# append calls = 6450
LinkStrand:	      2,097,152	  6,786,491,955	0.439	# append calls = 6450
LinkStrand:	      4,194,304	 13,549,807,155	0.439	# append calls = 6450
LinkStrand:	      8,388,608	 27,076,437,555	0.455	# append calls = 6450
LinkStrand:	     16,777,216	 54,129,698,355	0.484	# append calls = 6450
LinkStrand:	     33,554,432	108,236,219,955	0.501	# append calls = 6450
LinkStrand:	     67,108,864	216,449,263,155	0.435	# append calls = 6450

Since append calls are 2(breaks), then :

DNA strand           breaks      mean running time
ecoli.txt            645         0.0972632
ecoli_double.txt     1290        0.174158
ecoli_triple.txt     1935        0.263895
ecoliX5.txt          3225        0.481053

A graph of all of the running times plotted against the number of breaks with a linear fit model can be found at:
http://imgur.com/gallery/cXDr5E7/new
(done in Wolfram Mathematica)

Visual inspection of the graph shows that although running times vary around a mean value for each amount of breaks,
this variance does not depend on the amount of breaks. Also, the running time does not increase each time the 
resulting recombinant DNA strand's length is increased, so running time is not dependent on that. 
However, these groups' mean values do increase with the amount of breaks, and it can be seen that they are increasing
linearly (ecoli_double.txt mean running time is about twice the running time of ecoli.txt, ecoli5x.txt mean running
time is about 5 times the running time of ecoli.txt, etc.). Because mean running time is increasing liearly with 
breaks, it is safe to say that cutAndSplice for LinkStrand is O(B).
