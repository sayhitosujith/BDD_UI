package StepDefs.services.Practice;

//if the given arrays is {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0},
//        it should be changed to {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}

public class StringReplace {
    public static void moveZeroToEnd(int[] nums) {
        int index = 0; // this will keep track of all possitions
        //traverse
        for (int num : nums)
            if (num != 0) {
                nums[index++] = num;
            }

                //Placing all zero elements,fill the rest with the elements of the font
                while (index < nums.length) {
                    nums[index++] = 0;
                }
            }
                public static void main(String[] args)
                {
                    int[] nums = {1, 0, 0, 0, 9, 4, 0, 3, 0,2, 7, 2, 2, 0, 5, 6,0};
                    moveZeroToEnd(nums);

                    //print an array after moving
                    for (int num : nums) {
                        System.out.println(num + "");
                    }
                }
            }


