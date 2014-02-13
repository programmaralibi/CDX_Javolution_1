package command.evaluator;

import java.io.IOException;
import java.io.InputStream;
public class StringInputStream extends InputStream	{		private String s;		private int position;		private int markSize;		private int markPos;
		StringInputStream(String s)		{
			this.s = s;
			position = 0;			markSize = 0;			markPos = 0;
		}
		//Override
		public int available() //throws IOException
		{
			return s.length() - position;
		}
		//Override
		public void mark(int size)
		{
			markPos = position;
			markSize = size;
		}
		//Override
		public boolean markSupported() { return true; }
				//Override
		public int read() throws IOException
		{
			if(position >= s.length())
			{
				//throw new IOException("Invalid position.");				return -1;
			}
			return s.charAt(position++);		}
		public int read(char[] dest, int offset, int size)
		{
			if(position + size  >= s.length())
			{
				//return the rest of the string
				s.getChars(position,s.length(),dest,offset);				position = s.length();
				return -1;
			}
			else
			{
				//only return size;
				s.getChars(position,position + size,dest,offset);
				position += size;
				return size;
			}			//return s[position] to s[position+size-1]
			//start at byte[offset]
			//return # of bytes read or -1 if reach end of string
			//if dest == null, throw the bytes away
		}		public int read(char[] dest) //throws IOException
		{			return read(dest,0,s.length());		}
		//Override
		public synchronized void reset() throws IOException
		{
			if (position <= markPos + markSize)
			{
				position = markPos;
			}
			else
			{
				throw new IOException("Read too far past mark.");
			}

		}
		//Override
		public long skip(long offset) //throws IOException
		{
			if (position + offset >= s.length())
			{
				position = s.length();
				return s.length() - position;
			}
			else
			{
				position += offset;
				return offset;
			}
		}}