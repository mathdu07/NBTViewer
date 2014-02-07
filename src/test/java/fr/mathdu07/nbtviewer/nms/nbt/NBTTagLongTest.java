package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagLongTest {

	private static net.minecraft.server.v1_6_R3.NBTTagLong nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init(NBTTestUtil.CB_VERSION_TESTING);
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagLong("", 15815151541l);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagLong.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagLong.class);
	}
	
	@Test
	public void testCreateTag() {
		assertEquals(new NBTTagLong(new net.minecraft.server.v1_6_R3.NBTTagLong("", 25793l)), NBTTagLong.createTag(25793l));
	}

	@Test
	public void testNBTTagLong() {
		final NBTTagLong nbt = new NBTTagLong(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagLongException() {
		new NBTTagLong("a string");
	}
	
	@Test
	public void testGetData() {
		final NBTTagLong nbt = new NBTTagLong(nmsTag);
		assertTrue(nbt.getData() == nmsTag.data);
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagLong nbt = new NBTTagLong(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagLong nbt = new NBTTagLong(nmsTag), equal = new NBTTagLong(nmsTag);
		final NBTTagLong diff = new NBTTagLong(new net.minecraft.server.v1_6_R3.NBTTagLong("", 54l));
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagLong nbt = new NBTTagLong(nmsTag), clone = (NBTTagLong) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagLong nbt = new NBTTagLong(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}
