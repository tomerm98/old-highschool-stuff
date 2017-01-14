using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Windows.Forms;

namespace Shlomi
{
    class FilledPie
    {
        

      
      

       


       

        public int Width
        {
            get { return rect.Width; }
            set { rect.Width = value; }
        }
        public int Height
        {
            get { return rect.Height; }
            set { rect.Height = value; }
        }
        public int X
        {
            get { return rect.X; }
            set { rect.X = value; }
        }
        public int Y
        {
            get { return rect.Y; }
            set { rect.Y = value; }
        }

        public FilledPie()
        {
            Brush = new SolidBrush(Color.Black);
            Rect = new Rectangle(0, 0, 1, 1);
            StartAngle = 0F;
            SweepAngle = 0F;
            AnimationTickRate = 30;
         

        }
        public FilledPie(Rectangle rect, float startAngle, float sweepAngle, int animationTickRate)
        {

            Brush = new SolidBrush(Color.Black);
            Rect = rect;
            StartAngle = startAngle;
            SweepAngle = sweepAngle;
            AnimationTickRate = animationTickRate;
           
        }

        public FilledPie(Brush brush, Rectangle rect, float startAngle, float sweepAngle, int animationTickRate)
        {
            Brush = brush;
            Rect = rect;
            StartAngle = startAngle;
            SweepAngle = sweepAngle;
            AnimationTickRate = animationTickRate;
           
        }


        public void AnimateSize(float percentOfOldSize, int durationMS)
        {

            if (percentOfOldSize == 100) return;
            float widthNeeded = 0;
            float heightNeeded = 0;
            currentWidthToAdd = 0;
            currentHeightToAdd = 0;

            if (percentOfOldSize > 100)
            {
                widthNeeded = Rect.Width * (percentOfOldSize / 100) - rect.Width;
                heightNeeded = Rect.Height * (percentOfOldSize / 100) - rect.Height;
            }
            if (percentOfOldSize < 100)
            {
                widthNeeded = Rect.Width / (100 / percentOfOldSize) - rect.Width;
                heightNeeded = Rect.Height / (100 / percentOfOldSize) - rect.Height;
            }


            numOfTicksNeeded = durationMS / AnimationTickRate;

            widthPerTick = widthNeeded / numOfTicksNeeded;
            heightPerTick = heightNeeded / numOfTicksNeeded;


          //  timer.Start();

        }


        public bool IsPointOnPie(int x, int y)
        {

            //this is a mathematical formula that I barely understands. Credit goes to Alon Heller.

            int centerWidth = rect.X + (rect.Width / 2);
            int centerHeight = rect.Y + (rect.Height / 2);

            if (((4 * Math.Pow(x - centerWidth, 2)) / (rect.Width * rect.Width)) +
                ((4 * Math.Pow(y - centerHeight, 2)) / (rect.Height * rect.Height)) <= 1)
            {

                double calcedValue = RadiansToDegrees(Math.Atan2(y - centerHeight, x - centerWidth)) - startAngle;
                if (calcedValue < 0) calcedValue += 360;
                if (calcedValue < sweepAngle)
                    return true;
            }

            return false;
        }
        private double RadiansToDegrees(double radians)
        {
            return radians * (180.0 / Math.PI);
        }




        private Brush brush;
        public Brush Brush
        {
            get { return brush; }
            set { brush = value; }
        }

        private Rectangle rect;
        public Rectangle Rect
        {
            get { return rect; }
            set { rect = value; }
        }

        private float startAngle;
        public float StartAngle
        {
            get { return startAngle; }
            set
            {
                while (value > 360)
                    value -= 360;
                while (value < -360)
                    value += 360;
                startAngle = value;

            }
        }

        private float sweepAngle;
        public float SweepAngle
        {
            get { return sweepAngle; }
            set
            {
                if (value > 360)
                    value = 360;
                if (value < -360)
                    value = -360;
                sweepAngle = value;
            }
        }

        private int animationTickRate;
        public int AnimationTickRate
        {
            get { return animationTickRate; }
            set { animationTickRate = value; }
        }

        private float widthPerTick;
        public float WidthPerTick
        {
            get { return widthPerTick; }
            set { widthPerTick = value; }
        }

        private float heightPerTick;
        public float HeightPerTick
        {
            get { return heightPerTick; }
            set { heightPerTick = value; }
        }

        private float currentWidthToAdd;
        public float CurrentWidthToAdd
        {
            get { return currentWidthToAdd; }
            set { currentWidthToAdd = value; }
        }

        private float currentHeightToAdd;
        public float CurrentHeightToAdd
        {
            get { return currentHeightToAdd; }
            set { currentHeightToAdd = value; }
        }

        private int numOfTicksNeeded;
        public int NumOfTicksNeeded
        {
            get { return numOfTicksNeeded; }
            set { numOfTicksNeeded = value; }
        }
    }


}
