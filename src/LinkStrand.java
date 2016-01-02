import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class LinkStrand implements IDnaStrand {
	/**
	 * Create a strand representing an empty DNA strand, length of zero.
	 */
	public class Node{
		String info;
		Node next;
		
		Node(String s){
			info = s;
			next = null;
		}
	}
	private Node myFirst, myLast;
	private long mySize;
	private int myAppends;
	
	public LinkStrand() {
		//calls LinkStrand(String) with the empty string as the arg.
		this("");
	}

	/**
	 * Create a strand representing s. No error checking is done to see if s
	 * represents valid genomic/DNA data.
	 * 
	 * @param s
	 *            is the source of cgat data for this strand
	 */
	public LinkStrand(String s) {
		//uses the Node constructor to create node whose info is the arg string, and then then this node is assigned
		//to myFirst, and myFirst is assigned to myLast. mySize is assigned the length of the arg string s. 
		myFirst = new Node(s);
		myLast = myFirst;
		mySize = s.length();
		myAppends = 0;
	}

	/**
	 * Cut this strand at every occurrence of enzyme, essentially replacing
	 * every occurrence of enzyme with splicee.
	 * 
	 * @param enzyme
	 *            is the pattern/strand searched for and replaced
	 * @param splicee
	 *            is the pattern/strand replacing each occurrence of enzyme
	 * @return the new strand leaving the original strand unchanged.
	 */
	@Override
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		//just like SimpleStrand's cutAndSplice, but appending LinkStrand objects instead.
		if(myFirst != myLast){
			throw new RuntimeException("DNA strand made up of more than one node");
		}
		LinkStrand ret = null;
		int pos = 0;
		int start = 0;
		String search = myFirst.info;
		boolean first = true;
		
		while ((pos = search.indexOf(enzyme, pos)) >= 0) {
			if (first) {
				ret = new LinkStrand(search.substring(start, pos));
				first = false;
			} else {
				ret.append(search.substring(start, pos));

			}
			start = pos + enzyme.length();
			ret.append(splicee);
			pos++;
		}

		if (start < search.length()) {
			// NOTE: This is an important special case! If the enzyme
			// is never found, return an empty String.
			if (ret == null) {
				ret = new LinkStrand("");
			} else {
				ret.append(search.substring(start));
			}
		}
		return ret;
	}

	/**
	 * Initialize this strand so that it represents the value of source. No
	 * error checking is performed.
	 * 
	 * @param source
	 *            is the source of this enzyme
	 */
	@Override
	public void initializeFrom(String source) {
		//Resets myFirst.info to the source arg, resets myFirst.next to null, resets myLast to myFirst, sets mySize
		//to length of source, and resets myAppends to 0.
		myFirst.info = source;
		myFirst.next = null;
		myLast = myFirst;
		mySize = source.length();
		myAppends = 0;
	}

	/**
	 * Returns the number of nucleotides/base-pairs in this strand.
	 */
	@Override
	public long size() {
		return mySize;
	}

	/**
	 * Returns the sequence of DNA this object represents as a String
	 * 
	 * @return the sequence of DNA this represents
	 */
	@Override
	public String toString() {
		//concatenates the info inside every node beginning with the empty string and returns the final concatenation
		Node current = myFirst;
		StringBuilder ret = new StringBuilder();
		while(current!=null){
			ret.append(current.info);
			current=current.next;
		}
		return ret.toString();
	}
	

	/**
	 * Return some string identifying this class.
	 * 
	 * @return a string representing this strand and its characteristics
	 */
	@Override
	public String strandInfo() {
		return this.getClass().getName();
	}

	/**
	 * Append a strand of DNA to this strand. If the strand being appended is
	 * represented by a LinkStrand object then an efficient append is performed.
	 * 
	 * @param dna
	 *            is the strand being appended
	 */
	@Override
	public IDnaStrand append(IDnaStrand dna) {
		//checks if dna is an instance of LinkStrand and appends by modifying pointers in this. If not, then
		//appends dna using the append(String) method.
		if(dna instanceof LinkStrand){
			LinkStrand Ldna = (LinkStrand) dna;
			myLast.next = Ldna.myFirst;
			myLast = Ldna.myLast;
			mySize += Ldna.mySize;
			myAppends++;
		}
		else{
			append(dna.toString());
		}
		return this;
	}

	/**
	 * Simply append a strand of dna data to this strand.
	 * 
	 * @param dna
	 *            is the String appended to this strand
	 */
	@Override
	public IDnaStrand append(String dna) {
		//creates a new node called 'seq' that is constructed using dna, then shift over the LinkedStrand pointers
		//to accommodate this new node and make sure it becomes myLast.
		Node seq = new Node(dna);
		myLast.next = seq;
		myLast = seq;
		mySize += dna.length();
		myAppends++;
		return this;
	}

	/**
	 * Returns an IDnaStrand that is the reverse of this strand, e.g., for
	 * "CGAT" returns "TAGC"
	 * 
	 * @return reverse strand
	 */
	@Override
	public IDnaStrand reverse() {
		//Using Arun Ganesh's suggestions of a Reverse hashmap and a stack.
		Stack<String> dnaStack = new Stack<String>();
		HashMap<String, String> reverseMap = new HashMap<String, String>();
		LinkStrand ret = new LinkStrand();
		Node current = myFirst;
		while(current != null){
			String orig = current.info;
			if(reverseMap.get(orig) == null){
				reverseMap.put(orig, new StringBuilder(orig).reverse().toString());
			}
			String reversal = reverseMap.get(orig);
			dnaStack.push(reversal);
			current = current.next;
		}
		while(!dnaStack.isEmpty()){
			ret.append(dnaStack.pop());
		}
		return ret;
	}

	/**
	 * Returns a string that can be printed to reveal information about what
	 * this object has encountered as it is manipulated by append and
	 * cutAndSplice.
	 * 
	 * @return
	 */
	@Override
	public String getStats() {
		return String.format("# append calls = %d", myAppends);
	}

	/**
	 * Returns an ArrayList of Strings corresponding to the nodes in the linked
	 * list this class contains. That is, the first value of the list should be
	 * the info within the head of the linked list, the second value should be
	 * the info within the node the head points to, etc. The ArrayList returned
	 * should be generated at the time the method is called. That is, the
	 * ArrayList should have a scope of only this method (i.e. not global)
	 * 
	 * @return list of Strings corresponding to nodes
	 */
	public ArrayList<String> nodeList() {
		ArrayList<String> ret = new ArrayList<String>();
		Node current = myFirst;
		while(current!=null){
			ret.add(current.info);
			current = current.next;
		}
		return ret;
	}
	
	/*
	public static void main(String[] args){
		LinkStrand t=new LinkStrand("atgc");
		System.out.println(t.toString());
	}
	*/
}
