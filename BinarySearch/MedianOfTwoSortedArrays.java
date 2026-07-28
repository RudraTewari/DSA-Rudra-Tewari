/*
========================================
MEDIAN OF TWO SORTED ARRAYS - BINARY SEARCH
========================================

PROBLEM:
Find the median of two sorted arrays in O(log(min(n1,n2))) time.

--------------------------------------------------
CORE IDEA
--------------------------------------------------

Instead of merging arrays,
we partition both arrays such that:

Left Half  <=  Right Half

Meaning:

max(leftHalf) <= min(rightHalf)

If this condition is satisfied,
we found the correct partition.

--------------------------------------------------
WHY BINARY SEARCH?
--------------------------------------------------

We binary search on the SMALLER array
to reduce time complexity.

If nums1 is larger:
swap arrays.

Time Complexity:
O(log(min(n1,n2)))

--------------------------------------------------
PARTITION LOGIC
--------------------------------------------------

Suppose:

nums1 = [1 3 8]
nums2 = [7 9 10 11]

Possible partition:

nums1 -> [1 3 | 8]
nums2 -> [7 | 9 10 11]

Left side:
1 3 7

Right side:
8 9 10 11

We need:

left1 <= right2
AND
left2 <= right1

--------------------------------------------------
HOW MANY ELEMENTS SHOULD LEFT HALF CONTAIN?
--------------------------------------------------

total = n1 + n2

leftHalfSize = (total + 1) / 2

Why +1 ?

For odd length:
left half should contain 1 extra element.

Example:
total = 7

Left half size:
(7 + 1)/2 = 4

Right half size:
3

Median becomes:
max(leftHalf)

--------------------------------------------------
VARIABLES
--------------------------------------------------

cut1 = partition index in nums1
cut2 = remaining partition in nums2

cut2 = leftHalfSize - cut1

--------------------------------------------------
BOUNDARY HANDLING
--------------------------------------------------

When partition touches edges:

No left element:
Integer.MIN_VALUE

No right element:
Integer.MAX_VALUE

This avoids index out of bounds
and simplifies comparisons.

--------------------------------------------------
VALID PARTITION CONDITION
--------------------------------------------------

if(left1 <= right2 && left2 <= right1)

Then:

Correct partition found.

--------------------------------------------------
MEDIAN FORMULA
--------------------------------------------------

1. EVEN LENGTH

Median =
(max(left1,left2) + min(right1,right2)) / 2

Because two middle elements exist.

-----------------------------------

2. ODD LENGTH

Median =
max(left1,left2)

Because left side contains one extra element.

--------------------------------------------------
HOW BINARY SEARCH MOVES
--------------------------------------------------

Case 1:
left1 > right2

Meaning:
We included too many elements from nums1.

Move LEFT:
high = cut1 - 1

-----------------------------------

Case 2:
left2 > right1

Meaning:
We need more elements from nums1.

Move RIGHT:
low = cut1 + 1

--------------------------------------------------
TIME COMPLEXITY
--------------------------------------------------
Binary search on smaller array:

O(log(min(n1,n2)))
--------------------------------------------------
SPACE COMPLEXITY
--------------------------------------------------
O(1)
No extra space used.

==================================================
TEMPLATE MEMORY TRICK
==================================================

    1. Binary search on smaller array
    2. Partition both arrays
    3. Check:
    left1 <= right2
    left2 <= right1
    4. If valid:
        even -> average of middle two
        odd  -> max(left)
    5. Else:
        adjust binary search
==================================================
*/
class MedianOfTwoSortedArrays{
    public double findMedianSortedArrays(int[] nums1,int[] nums2){
        int n1=nums1.length;
        int n2 = nums2.length;
        if(n1>n2){
            return findMedianSortedArrays(nums2,nums1);
        }

        int low=0,high=n1;
        int total=n1+n2;
        while(low <= high){
            int cut1 = low+(high-low)/2;
            int cut2 = (total+1)/2 - cut1;

            int left1 = (cut1==0) ? Integer.MIN_VALUE : nums1[cut1-1];
            int left2 = (cut2==0) ? Integer.MIN_VALUE : nums2[cut2-1];

            int right1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];

            if(left1 <= right2 && left2 <= right1){
                // Even Length
                if((total&1) == 0){
                    return (double)(Math.max(left1,left2)+Math.min(right1,right2))/2.0f;
                }else{ //Odd Length
                    return (double)(Math.max(left1,left2));
                }
            }else if(left1 > right2){
                high=cut1-1;
            }else{
                low=cut1+1;
            }
        }
        return 0;
    }
    public static void main(String[] args){
        int[] nums1={1,2,2};
        int[] nums2={3,4,6};

        MedianOfTwoSortedArrays obj= new MedianOfTwoSortedArrays();
        double ans = obj.findMedianSortedArrays(nums1,nums2);
        System.out.println(ans);
    }
}