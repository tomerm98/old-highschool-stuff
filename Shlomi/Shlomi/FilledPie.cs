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
        private float widthPerTick;
        private float heightPerTick;
        private float currentWidthToAdd;
        private float currentHeightToAdd;
        private int numOfTicksNeeded;
        private Timer timer;


        public FilledPie()
        {
            Brush = new SolidBrush(Color.Black);
            Rect = new Rectangle();
            StartAngle = 0F;
            SweepAngle = 0F;
            AnimationTickRate = 30;
            InitializeTimer();

        }
        public FilledPie(Rectangle rect, float startAngle, float sweepAngle, int animationTickRate)
        {

            Brush = new SolidBrush(Color.Black);
            Rect = rect;
            StartAngle = startAngle;
            SweepAngle = sweepAngle;
            AnimationTickRate = animationTickRate;
            InitializeTimer();
        }

        public FilledPie(Brush brush, Rectangle rect, float startAngle, float sweepAngle, int animationTickRate)
        {
            Brush = brush;
            Rect = rect;
            StartAngle = startAngle;
            SweepAngle = sweepAngle;
            AnimationTickRate = animationTickRate;
            InitializeTimer();
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
           

            timer.Start();

        }

        private void InitializeTimer()
        {
            timer = new Timer();
            timer.Interval = AnimationTickRate;
            timer.Enabled = false;
            timer.Tick += Timer_Tick;
        }

        private void Timer_Tick(object sender, EventArgs e)
        {
            if (numOfTicksNeeded > 0)
            {
                currentWidthToAdd += widthPerTick;
                currentHeightToAdd += heightPerTick;
                if (currentWidthToAdd >= 1 || currentWidthToAdd <= -1)
                {
                    rect.Width += (int)currentWidthToAdd;
                    currentWidthToAdd = currentWidthToAdd % 1;
                }

                if (currentHeightToAdd >= 1 || currentHeightToAdd <= -1)
                {
                    rect.Height += (int)currentHeightToAdd;
                    currentHeightToAdd = currentHeightToAdd % 1;
                }




                numOfTicksNeeded--;
            }
            else
                timer.Stop();

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
    }
}
