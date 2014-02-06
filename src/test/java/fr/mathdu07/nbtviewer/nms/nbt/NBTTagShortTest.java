package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagShortTest {

	private static net.minecraft.server.v1_6_R3.NBTTagShort nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init(NBTTestUtil.CB_VERSION_TESTING);
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagShort("", (short) 6);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagShort.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagShort.class);
	}

	@Test
	public void testNBTTagShort() {
		final NBTTagShort nbt = new NBTTagShort(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagShortException() {
		new NBTTagShort("a string");
	}
	
	@Test
	public void testGetData() {
		final NBTTagShort nbt = new NBTTagShort(nmsTag);
		assertTrue(nbt.getData() == nmsTag.data);
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagShort nbt = new NBTTagShort(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagShort nbt = new NBTTagShort(nmsTag), equal = new NBTTagShort(nmsTag);
		final NBTTagShort diff = new NBTTagShort(new net.minecraft.server.v1_6_R3.NBTTagShort("", (short) 2540));
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagShort nbt = new NBTTagShort(nmsTag), clone = (NBTTagShort) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagShort nbt = new NBTTagShort(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}
