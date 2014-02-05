package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagByteTest {
	
	private static net.minecraft.server.v1_6_R3.NBTTagByte nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init("1.6.4-R2.0");
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagByte("", (byte) 5);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagByte.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagByte.class);
	}

	@Test
	public void testNBTTagByte() {
		final NBTTagByte nbt = new NBTTagByte(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagByteException() {
		new NBTTagByte("a string");
	}
	
	@Test
	public void testGetData() {
		final NBTTagByte nbt = new NBTTagByte(nmsTag);
		assertTrue(nbt.getData() == nmsTag.data);
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagByte nbt = new NBTTagByte(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagByte nbt = new NBTTagByte(nmsTag), equal = new NBTTagByte(nmsTag);
		final NBTTagByte diff = new NBTTagByte(new net.minecraft.server.v1_6_R3.NBTTagByte("", (byte) -4));
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagByte nbt = new NBTTagByte(nmsTag), clone = (NBTTagByte) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagByte nbt = new NBTTagByte(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}
