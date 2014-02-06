package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagFloatTest {

	private static net.minecraft.server.v1_6_R3.NBTTagFloat nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init(NBTTestUtil.CB_VERSION_TESTING);
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagFloat("", 24.5f);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagFloat.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagFloat.class);
	}

	@Test
	public void testNBTTagFloat() {
		final NBTTagFloat nbt = new NBTTagFloat(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagFloatException() {
		new NBTTagFloat("a string");
	}
	
	@Test
	public void testGetData() {
		final NBTTagFloat nbt = new NBTTagFloat(nmsTag);
		assertTrue(nbt.getData() == nmsTag.data);
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagFloat nbt = new NBTTagFloat(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagFloat nbt = new NBTTagFloat(nmsTag), equal = new NBTTagFloat(nmsTag);
		final NBTTagFloat diff = new NBTTagFloat(new net.minecraft.server.v1_6_R3.NBTTagFloat("", -3.0f));
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagFloat nbt = new NBTTagFloat(nmsTag), clone = (NBTTagFloat) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagFloat nbt = new NBTTagFloat(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}
