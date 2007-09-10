//
// In order to convert some functionality to Visual C#, the Java Language Conversion Assistant
// creates "support classes" that duplicate the original functionality.  
//
// Support classes replicate the functionality of the original code, but in some cases they are 
// substantially different architecturally. Although every effort is made to preserve the 
// original architecture of the application in the converted project, the user should be aware that 
// the primary goal of these support classes is to replicate functionality, and that at times 
// the architecture of the resulting solution may differ somewhat.
//

using System;

/// <summary>
/// Contains conversion support elements such as classes, interfaces and static methods.
/// </summary>
public class SupportClass
{
	/// <summary>
	/// This class manages array operations.
	/// </summary>
	public class ArraySupport
	{
		/// <summary>
		/// Compares the entire members of one array whith the other one.
		/// </summary>
		/// <param name="array1">The array to be compared.</param>
		/// <param name="array2">The array to be compared with.</param>
		/// <returns>True if both arrays are equals otherwise it returns false.</returns>
		/// <remarks>Two arrays are equal if they contains the same elements in the same order.</remarks>
		public static bool Equals(System.Array array1, System.Array array2)
		{
			bool result = false;
			if ((array1 == null) && (array2 == null))
				result = true;
			else if ((array1 != null) && (array2 != null))
			{
				if (array1.Length == array2.Length)
				{
					int length = array1.Length;
					result = true;
					for (int index = 0; index < length; index++)
					{
						if (!(array1.GetValue(index).Equals(array2.GetValue(index))))
						{
							result = false;
							break;
						}
					}
				}
			}
			return result;
		}

		/// <summary>
		/// Fills the array with an specific value from an specific index to an specific index.
		/// </summary>
		/// <param name="array">The array to be filled.</param>
		/// <param name="fromindex">The first index to be filled.</param>
		/// <param name="toindex">The last index to be filled.</param>
		/// <param name="val">The value to fill the array with.</param>
		public static void Fill(System.Array array, System.Int32 fromindex, System.Int32 toindex, System.Object val)
		{
			System.Object Temp_Object = val;
			System.Type elementtype = array.GetType().GetElementType();
			if (elementtype != val.GetType())
				Temp_Object = System.Convert.ChangeType(val, elementtype);
			if (array.Length == 0)
				throw (new System.NullReferenceException());
			if (fromindex > toindex)
				throw (new System.ArgumentException());
			if ((fromindex < 0) || ((System.Array)array).Length < toindex)
				throw (new System.IndexOutOfRangeException());
			for (int index = (fromindex > 0) ? fromindex-- : fromindex; index < toindex; index++)
				array.SetValue(Temp_Object, index);
		}

		/// <summary>
		/// Fills the array with an specific value.
		/// </summary>
		/// <param name="array">The array to be filled.</param>
		/// <param name="val">The value to fill the array with.</param>
		public static void Fill(System.Array array, System.Object val)
		{
			Fill(array, 0, array.Length, val);
		}
	}


	/*******************************/
	/// <summary>
	/// This class provides functionality not found in .NET collection-related interfaces.
	/// </summary>
	public class ICollectionSupport
	{
		/// <summary>
		/// Adds a new element to the specified collection.
		/// </summary>
		/// <param name="c">Collection where the new element will be added.</param>
		/// <param name="obj">Object to add.</param>
		/// <returns>true</returns>
		public static bool Add(System.Collections.ICollection c, System.Object obj)
		{
			bool added = false;
			//Reflection. Invoke either the "add" or "Add" method.
			System.Reflection.MethodInfo method;
			try
			{
				//Get the "add" method for proprietary classes
				method = c.GetType().GetMethod("Add");
				if (method == null)
					method = c.GetType().GetMethod("add");
				int index = (int) method.Invoke(c, new System.Object[] {obj});
				if (index >=0)	
					added = true;
			}
			catch (System.Exception e)
			{
				throw e;
			}
			return added;
		}

		/// <summary>
		/// Adds all of the elements of the "c" collection to the "target" collection.
		/// </summary>
		/// <param name="target">Collection where the new elements will be added.</param>
		/// <param name="c">Collection whose elements will be added.</param>
		/// <returns>Returns true if at least one element was added, false otherwise.</returns>
		public static bool AddAll(System.Collections.ICollection target, System.Collections.ICollection c)
		{
			System.Collections.IEnumerator e = new System.Collections.ArrayList(c).GetEnumerator();
			bool added = false;

			//Reflection. Invoke "addAll" method for proprietary classes
			System.Reflection.MethodInfo method;
			try
			{
				method = target.GetType().GetMethod("addAll");

				if (method != null)
					added = (bool) method.Invoke(target, new System.Object[] {c});
				else
				{
					method = target.GetType().GetMethod("Add");
					while (e.MoveNext() == true)
					{
						bool tempBAdded =  (int) method.Invoke(target, new System.Object[] {e.Current}) >= 0;
						added = added ? added : tempBAdded;
					}
				}
			}
			catch (System.Exception ex)
			{
				throw ex;
			}
			return added;
		}

		/// <summary>
		/// Removes all the elements from the collection.
		/// </summary>
		/// <param name="c">The collection to remove elements.</param>
		public static void Clear(System.Collections.ICollection c)
		{
			//Reflection. Invoke "Clear" method or "clear" method for proprietary classes
			System.Reflection.MethodInfo method;
			try
			{
				method = c.GetType().GetMethod("Clear");

				if (method == null)
					method = c.GetType().GetMethod("clear");

				method.Invoke(c, new System.Object[] {});
			}
			catch (System.Exception e)
			{
				throw e;
			}
		}

		/// <summary>
		/// Determines whether the collection contains the specified element.
		/// </summary>
		/// <param name="c">The collection to check.</param>
		/// <param name="obj">The object to locate in the collection.</param>
		/// <returns>true if the element is in the collection.</returns>
		public static bool Contains(System.Collections.ICollection c, System.Object obj)
		{
			bool contains = false;

			//Reflection. Invoke "contains" method for proprietary classes
			System.Reflection.MethodInfo method;
			try
			{
				method = c.GetType().GetMethod("Contains");

				if (method == null)
					method = c.GetType().GetMethod("contains");

				contains = (bool)method.Invoke(c, new System.Object[] {obj});
			}
			catch (System.Exception e)
			{
				throw e;
			}

			return contains;
		}

		/// <summary>
		/// Determines whether the collection contains all the elements in the specified collection.
		/// </summary>
		/// <param name="target">The collection to check.</param>
		/// <param name="c">Collection whose elements would be checked for containment.</param>
		/// <returns>true id the target collection contains all the elements of the specified collection.</returns>
		public static bool ContainsAll(System.Collections.ICollection target, System.Collections.ICollection c)
		{						
			System.Collections.IEnumerator e =  c.GetEnumerator();

			bool contains = false;

			//Reflection. Invoke "containsAll" method for proprietary classes or "Contains" method for each element in the collection
			System.Reflection.MethodInfo method;
			try
			{
				method = target.GetType().GetMethod("containsAll");

				if (method != null)
					contains = (bool)method.Invoke(target, new Object[] {c});
				else
				{					
					method = target.GetType().GetMethod("Contains");
					while (e.MoveNext() == true)
					{
						if ((contains = (bool)method.Invoke(target, new Object[] {e.Current})) == false)
							break;
					}
				}
			}
			catch (System.Exception ex)
			{
				throw ex;
			}

			return contains;
		}

		/// <summary>
		/// Removes the specified element from the collection.
		/// </summary>
		/// <param name="c">The collection where the element will be removed.</param>
		/// <param name="obj">The element to remove from the collection.</param>
		public static bool Remove(System.Collections.ICollection c, System.Object obj)
		{
			bool changed = false;

			//Reflection. Invoke "remove" method for proprietary classes or "Remove" method
			System.Reflection.MethodInfo method;
			try
			{
				method = c.GetType().GetMethod("remove");

				if (method != null)
					method.Invoke(c, new System.Object[] {obj});
				else
				{
					method = c.GetType().GetMethod("Contains");
					changed = (bool)method.Invoke(c, new System.Object[] {obj});
					method = c.GetType().GetMethod("Remove");
					method.Invoke(c, new System.Object[] {obj});
				}
			}
			catch (System.Exception e)
			{
				throw e;
			}

			return changed;
		}

		/// <summary>
		/// Removes all the elements from the specified collection that are contained in the target collection.
		/// </summary>
		/// <param name="target">Collection where the elements will be removed.</param>
		/// <param name="c">Elements to remove from the target collection.</param>
		/// <returns>true</returns>
		public static bool RemoveAll(System.Collections.ICollection target, System.Collections.ICollection c)
		{
			System.Collections.ArrayList al = ToArrayList(c);
			System.Collections.IEnumerator e = al.GetEnumerator();

			//Reflection. Invoke "removeAll" method for proprietary classes or "Remove" for each element in the collection
			System.Reflection.MethodInfo method;
			try
			{
				method = target.GetType().GetMethod("removeAll");

				if (method != null)
					method.Invoke(target, new System.Object[] {al});
				else
				{
					method = target.GetType().GetMethod("Remove");
					System.Reflection.MethodInfo methodContains = target.GetType().GetMethod("Contains");

					while (e.MoveNext() == true)
					{
						while ((bool) methodContains.Invoke(target, new System.Object[] {e.Current}) == true)
							method.Invoke(target, new System.Object[] {e.Current});
					}
				}
			}
			catch (System.Exception ex)
			{
				throw ex;
			}
			return true;
		}

		/// <summary>
		/// Retains the elements in the target collection that are contained in the specified collection
		/// </summary>
		/// <param name="target">Collection where the elements will be removed.</param>
		/// <param name="c">Elements to be retained in the target collection.</param>
		/// <returns>true</returns>
		public static bool RetainAll(System.Collections.ICollection target, System.Collections.ICollection c)
		{
			System.Collections.IEnumerator e = new System.Collections.ArrayList(target).GetEnumerator();
			System.Collections.ArrayList al = new System.Collections.ArrayList(c);

			//Reflection. Invoke "retainAll" method for proprietary classes or "Remove" for each element in the collection
			System.Reflection.MethodInfo method;
			try
			{
				method = c.GetType().GetMethod("retainAll");

				if (method != null)
					method.Invoke(target, new System.Object[] {c});
				else
				{
					method = c.GetType().GetMethod("Remove");

					while (e.MoveNext() == true)
					{
						if (al.Contains(e.Current) == false)
							method.Invoke(target, new System.Object[] {e.Current});
					}
				}
			}
			catch (System.Exception ex)
			{
				throw ex;
			}

			return true;
		}

		/// <summary>
		/// Returns an array containing all the elements of the collection.
		/// </summary>
		/// <returns>The array containing all the elements of the collection.</returns>
		public static System.Object[] ToArray(System.Collections.ICollection c)
		{	
			int index = 0;
			System.Object[] objects = new System.Object[c.Count];
			System.Collections.IEnumerator e = c.GetEnumerator();

			while (e.MoveNext())
				objects[index++] = e.Current;

			return objects;
		}

		/// <summary>
		/// Obtains an array containing all the elements of the collection.
		/// </summary>
		/// <param name="objects">The array into which the elements of the collection will be stored.</param>
		/// <returns>The array containing all the elements of the collection.</returns>
		public static System.Object[] ToArray(System.Collections.ICollection c, System.Object[] objects)
		{	
			int index = 0;

			System.Type type = objects.GetType().GetElementType();
			System.Object[] objs = (System.Object[]) Array.CreateInstance(type, c.Count );

			System.Collections.IEnumerator e = c.GetEnumerator();

			while (e.MoveNext())
				objs[index++] = e.Current;

			//If objects is smaller than c then do not return the new array in the parameter
			if (objects.Length >= c.Count)
				objs.CopyTo(objects, 0);

			return objs;
		}

		/// <summary>
		/// Converts an ICollection instance to an ArrayList instance.
		/// </summary>
		/// <param name="c">The ICollection instance to be converted.</param>
		/// <returns>An ArrayList instance in which its elements are the elements of the ICollection instance.</returns>
		public static System.Collections.ArrayList ToArrayList(System.Collections.ICollection c)
		{
			System.Collections.ArrayList tempArrayList = new System.Collections.ArrayList();
			System.Collections.IEnumerator tempEnumerator = c.GetEnumerator();
			while (tempEnumerator.MoveNext())
				tempArrayList.Add(tempEnumerator.Current);
			return tempArrayList;
		}
	}


}
