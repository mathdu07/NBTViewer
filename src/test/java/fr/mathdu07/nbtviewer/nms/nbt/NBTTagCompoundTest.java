package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagCompoundTest {
	
	private net.minecraft.server.v1_6_R3.NBTTagCompound nmsTag;
	private net.minecraft.server.v1_6_R3.NBTTagByte nmsByte;
	private net.minecraft.server.v1_6_R3.NBTTagByteArray nmsByteArray;
	private net.minecraft.server.v1_6_R3.NBTTagCompound nmsCompound;
	private net.minecraft.server.v1_6_R3.NBTTagDouble nmsDouble;
	private net.minecraft.server.v1_6_R3.NBTTagFloat nmsFloat;
	private net.minecraft.server.v1_6_R3.NBTTagInt nmsInt;
	private net.minecraft.server.v1_6_R3.NBTTagIntArray nmsIntArray;
	private net.minecraft.server.v1_6_R3.NBTTagList nmsList;
	private net.minecraft.server.v1_6_R3.NBTTagLong nmsLong;
	private net.minecraft.server.v1_6_R3.NBTTagShort nmsShort;
	private net.minecraft.server.v1_6_R3.NBTTagString nmsString;
	
	private NBTTagCompound tagCompound;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init(NBTTestUtil.CB_VERSION_TESTING);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Before
	public void setUp() throws Exception {
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagCompound();
		nmsByte = new net.minecraft.server.v1_6_R3.NBTTagByte("", (byte) 1);
		nmsByteArray = new net.minecraft.server.v1_6_R3.NBTTagByteArray("", new byte[] {5, -7});
		nmsCompound = new net.minecraft.server.v1_6_R3.NBTTagCompound();
		nmsDouble = new net.minecraft.server.v1_6_R3.NBTTagDouble("", 15.0);
		nmsFloat = new net.minecraft.server.v1_6_R3.NBTTagFloat("", 14.f);
		nmsInt = new net.minecraft.server.v1_6_R3.NBTTagInt("", 543);
		nmsIntArray = new net.minecraft.server.v1_6_R3.NBTTagIntArray("", new int[] {8544, 8453152});
		nmsList = new net.minecraft.server.v1_6_R3.NBTTagList();
		nmsLong = new net.minecraft.server.v1_6_R3.NBTTagLong("", 1548635949l);
		nmsShort = new net.minecraft.server.v1_6_R3.NBTTagShort("", (short) 2546);
		nmsString = new net.minecraft.server.v1_6_R3.NBTTagString("", "string");
		
		nmsTag.set("byte", nmsByte);
		nmsTag.set("byteArray", nmsByteArray);
		nmsTag.set("compound", nmsCompound);
		nmsTag.set("double", nmsDouble);
		nmsTag.set("float", nmsFloat);
		nmsTag.set("int", nmsInt);
		nmsTag.set("intArray", nmsIntArray);
		nmsTag.set("list", nmsList);
		nmsTag.set("long", nmsLong);
		nmsTag.set("short", nmsShort);
		nmsTag.set("string", nmsString);
		
		tagCompound = new NBTTagCompound(nmsTag);
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagCompound.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagCompound.class);
	}

	@Test
	public void testNBTTagCompound() {
		final NBTTagCompound nbt = new NBTTagCompound(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagCompoundException() {
		new NBTTagCompound("a string");
	}
	
	@Test
	public void testHasKey() {
		assertTrue(tagCompound.hasKey("byte"));
		assertFalse(tagCompound.hasKey("unkown key"));
	}
	
	@Test
	public void testRemove() {
		tagCompound.remove("byte");
		assertFalse(tagCompound.hasKey("byte"));
	}
	
	@Test
	public void testIsEmpty() {
		final NBTTagCompound empty = new NBTTagCompound(nmsCompound);
		assertFalse(tagCompound.isEmpty());
		assertTrue(empty.isEmpty());
	}
	
	@Test
	public void testSet() {
		tagCompound.set("test", new NBTTagInt(nmsInt));
		assertEquals(nmsInt, tagCompound.get("test").nmsTag);
	}
	
	@Test
	public void testSetByte() {
		tagCompound.setByte("test", (byte) 1);
		assertTrue(tagCompound.getByte("test") == 1);
	}
	
	@Test
	public void testSetShort() {
		tagCompound.setShort("test", (short) 1475);
		assertTrue(tagCompound.getShort("test") == 1475);
	}
	
	@Test
	public void testSetInt() {
		tagCompound.setInt("test", 65814);
		assertTrue(tagCompound.getInt("test") == 65814);
	}
	
	@Test
	public void testSetLong() {
		tagCompound.setLong("test",  24l);
		assertTrue(tagCompound.getLong("test") == 24l);
	}
	
	@Test
	public void testSetFloat() {
		tagCompound.setFloat("test", 15.9f);
		assertTrue(tagCompound.getFloat("test") == 15.9f);
	}
	
	@Test
	public void testSetDouble() {
		tagCompound.setDouble("test", 0.87d);
		assertTrue(tagCompound.getDouble("test") == 0.87d);
	}
	
	@Test
	public void testSetString() {
		tagCompound.setString("test", "Testing String");
		assertEquals("Testing String", tagCompound.getString("test"));
	}
	
	@Test
	public void testSetByteArray() {
		tagCompound.setByteArray("test", new byte[] {-1, 0, 1, 2, 3});
		assertTrue(Arrays.equals(new byte[] {-1,  0, 1, 2, 3}, tagCompound.getByteArray("test")));
	}
	
	@Test
	public void testSetIntArray() {
		tagCompound.setIntArray("test", new int[] {-1, 0, 1, 2, 3});
		assertTrue(Arrays.equals(new int[] {-1,  0, 1, 2, 3}, tagCompound.getIntArray("test")));
	}
	
	@Test
	public void testSetCompound() {
		tagCompound.setCompound("test", new NBTTagCompound(nmsCompound));
		assertEquals(nmsCompound, tagCompound.getCompound("test").nmsTag);
	}
	
	@Test
	public void testSetBoolean() {
		tagCompound.setBoolean("test", false);
		assertTrue(false == tagCompound.getBoolean("test"));
	}
	
	@Test
	public void testGet() {
		assertEquals(nmsByte, tagCompound.get("byte").nmsTag);
		assertEquals(nmsInt, tagCompound.get("int").nmsTag);
		assertEquals(nmsList, tagCompound.get("list").nmsTag);
	}
	
	@Test
	public void testGetByte() {
		assertEquals(nmsByte.data, tagCompound.getByte("byte"));
	}
	
	@Test
	public void testGetShort() {
		assertEquals(nmsShort.data, tagCompound.getShort("short"));
	}
	
	@Test
	public void testGetInt() {
		assertEquals(nmsInt.data, tagCompound.getInt("int"));
	}
	
	@Test
	public void testGetLong() {
		assertEquals(nmsLong.data, tagCompound.getLong("long"));
	}
	
	@Test
	public void testGetFloat() {
		assertTrue(nmsFloat.data == tagCompound.getFloat("float"));
	}
	
	@Test
	public void testGetDouble() {
		assertTrue(nmsDouble.data == tagCompound.getDouble("double"));
	}
	
	@Test
	public void testGetString() {
		assertEquals(nmsString.data, tagCompound.getString("string"));
	}
	
	@Test
	public void testGetByteArray() {
		assertTrue(Arrays.equals(nmsByteArray.data, tagCompound.getByteArray("byteArray")));
	}
	
	@Test
	public void testGetIntyArray() {
		assertTrue(Arrays.equals(nmsIntArray.data, tagCompound.getIntArray("intArray")));
	}
	
	@Test
	public void testGetCompound() {
		assertEquals(nmsCompound, tagCompound.getCompound("compound").nmsTag);
	}
	
	@Test
	public void testGetList() {
		assertEquals(nmsList, tagCompound.getList("list").nmsTag);
	}
	
	@Test
	public void testGetBoolean() {
		assertEquals(true, tagCompound.getBoolean("byte"));
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagCompound nbt = new NBTTagCompound(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagCompound nbt = new NBTTagCompound(nmsTag), equal = new NBTTagCompound(nmsTag);
		final NBTTagCompound diff = new NBTTagCompound(new net.minecraft.server.v1_6_R3.NBTTagCompound());
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagCompound nbt = new NBTTagCompound(nmsTag), clone = (NBTTagCompound) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagCompound nbt = new NBTTagCompound(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}
