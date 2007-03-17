/** 
* Copyright (c) 2007, Ritu Jain, Chinmay Nagarkar and Sahi Technologies Pvt Ltd
* All rights reserved.
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the Sahi Technologies Pvt. Ltd./Esahi.com  nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
* EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
using System;
namespace org.drools.dotnet.examples
{
	/*
	* Copyright 2005 JBoss Inc
	* 
	* Licensed under the Apache License, Version 2.0 (the "License");
	* you may not use this file except in compliance with the License.
	* You may obtain a copy of the License at
	* 
	*      http://www.apache.org/licenses/LICENSE-2.0
	* 
	* Unless required by applicable law or agreed to in writing, software
	* distributed under the License is distributed on an "AS IS" BASIS,
	* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	* See the License for the specific language governing permissions and
	* limitations under the License.
	*/
	
	
	
	
	
	
	/// <author>  Alexander Bagerman
	/// 
	/// </author>
	public class WaltzUtil
	{
		private static double PI = 3.1415927;
		
		private static int MOD_NUM = 100;
		
		private static int get_y(int val)
		{
			return val % MOD_NUM;
		}
		
		private static int get_x(int val)
		{
			return (int) (val / MOD_NUM);
		}
		
		/// <summary>************************************************************************
		/// This function is passed two points and calculates the angle between the
		/// line defined by these points and the x-axis.
		/// ************************************************************************
		/// </summary>
		private static double get_angle(int p1, int p2)
		{
			int delta_x, delta_y;
			double ret = 0.0;
			/*
			* Calculate (x2 - x1) and (y2 - y1). The points are passed in the form
			* x1y1 and x2y2. get_x() and get_y() are passed these points and return
			* the x and y values respectively. For example, get_x(1020) returns 10.
			*/
			delta_x = get_x(p2) - get_x(p1);
			delta_y = get_y(p2) - get_y(p1);
			
			if (delta_x == 0)
			{
				if (delta_y > 0)
				{
					ret = PI / 2;
				}
				else if (delta_y < 0)
				{
					ret = (- PI) / 2;
				}
			}
			else if (delta_y == 0)
			{
				if (delta_x > 0)
				{
					ret = 0.0;
				}
				else if (delta_x < 0)
				{
					ret = PI;
				}
			}
			else
			{
				ret = System.Math.Atan2(delta_y, delta_x);
			}
			return ret;
		}
		
		/// <summary>************************************************************************
		/// This procedure is passed the basepoint of the intersection of two lines
		/// as well as the other two endpoints of the lines and calculates the angle
		/// inscribed by these three points.
		/// ************************************************************************
		/// </summary>
		private static double inscribed_angle(int basepoint, int p1, int p2)
		{
			double angle1, angle2, temp;
			
			/*
			* Get the angle between line #1 and the origin and the angle between
			* line #2 and the origin, and then subtract these values.
			*/
			angle1 = get_angle(basepoint, p1);
			angle2 = get_angle(basepoint, p2);
			temp = angle1 - angle2;
			if (temp < 0.0)
				temp = - temp;
			
			/*
			* We always want the smaller of the two angles inscribed, so if the
			* answer is greater than 180 degrees, calculate the smaller angle and
			* return it.
			*/
			if (temp > PI)
				temp = 2 * PI - temp;
			if (temp < 0.0)
				return (- temp);
			return (temp);
		}
		
		public static Junction make_3_junction(int basepoint, int p1, int p2, int p3)
		{
			int shaft, barb1, barb2;
			double angle12, angle13, angle23;
			double sum, sum1213, sum1223, sum1323;
			double delta;
			System.String j_type;
			
			angle12 = inscribed_angle(basepoint, p1, p2);
			angle13 = inscribed_angle(basepoint, p1, p3);
			angle23 = inscribed_angle(basepoint, p2, p3);
			
			sum1213 = angle12 + angle13;
			sum1223 = angle12 + angle23;
			sum1323 = angle13 + angle23;
			
			if (sum1213 < sum1223)
			{
				if (sum1213 < sum1323)
				{
					sum = sum1213;
					shaft = p1;
					barb1 = p2;
					barb2 = p3;
				}
				else
				{
					sum = sum1323;
					shaft = p3;
					barb1 = p1;
					barb2 = p2;
				}
			}
			else
			{
				if (sum1223 < sum1323)
				{
					sum = sum1223;
					shaft = p2;
					barb1 = p1;
					barb2 = p3;
				}
				else
				{
					sum = sum1323;
					shaft = p3;
					barb1 = p1;
					barb2 = p2;
				}
			}
			
			delta = sum - PI;
			if (delta < 0.0)
				delta = - delta;
			
			if (delta < 0.001)
			{
				j_type = Junction.TEE;
			}
			else if (sum > PI)
			{
				j_type = Junction.FORK;
			}
			else
			{
				j_type = Junction.ARROW;
			}
			
			return new Junction(barb1, shaft, barb2, basepoint, j_type);
		}
	}
}