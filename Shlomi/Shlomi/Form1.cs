using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Windows.Forms;



namespace Shlomi
{
    public partial class Form1 : Form
    {
        const int numOfPies = 6;
        const int animationTickRate = 30;
        const int animationDurationMS = 500;
        const float percentOfRectSizeFromScreenHeight = 30F;
        const float percentOfOldSizeForAnimation = 170F;
        
        int screenCenterX;
        int screenCenterY;
        int rectWidth;
        int rectHeight;
        bool animating;
        System.Timers.Timer tmrAnimationStarter;



        Rectangle[] rects;
        FilledPie[] pies;

        public Form1()
        {
            InitializeComponent();
        }



        private void Form1_Load(object sender, EventArgs e)
        {

           
            
            animating = false;
            tmrAnimationStarter = new System.Timers.Timer();
            tmrAnimationStarter.AutoReset = false;
            tmrAnimationStarter.Elapsed += TmrAnimationStarter_Elapsed; 
            InitializeProperties();
            SolidBrush[] brushes = {
                new SolidBrush(Color.Black),
                new SolidBrush(Color.Red),
                new SolidBrush(Color.Blue),
                new SolidBrush(Color.Green),
                new SolidBrush(Color.Orange),
                new SolidBrush(Color.Pink),
                new SolidBrush(Color.Purple),
                new SolidBrush(Color.Yellow)
                

            };
           rects = new Rectangle[numOfPies];
             pies = new FilledPie[numOfPies];
            int sweepAngle, startAngle;
            for (int i = 0; i < numOfPies; i++)
            {
                rects[i] = new Rectangle(screenCenterX - rectWidth / 2, screenCenterY - rectHeight / 2,
                                         rectWidth, rectHeight);
                sweepAngle = 360 / numOfPies;
                startAngle = sweepAngle * i;
            
                pies[i] = new FilledPie(brushes[i],rects[i], startAngle, sweepAngle, animationTickRate);
            }


            tmrMainAnimation.Interval = animationTickRate;
            tmrMainAnimation.Start();


        }

        

        private void InitializeProperties()
        {
            screenCenterX = ClientSize.Width / 2;
            screenCenterY = ClientSize.Height / 2;
            rectHeight = (int)(ClientSize.Width * (percentOfRectSizeFromScreenHeight / 100));
            rectWidth = rectHeight;
        }



        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            foreach (FilledPie fp in pies)
                e.Graphics.FillPie(fp.Brush, fp.Rect, fp.StartAngle, fp.SweepAngle);
           
        }

        int clickedPieIndex;
        int currentPieIndex;
        
        private void Form1_Click(object sender, EventArgs e)
        {
            if (!animating)
            {
                Point curserLocationInForm = PointToClient(Cursor.Position);
                for (clickedPieIndex = 0; clickedPieIndex < pies.Length; clickedPieIndex++)
                {
                    if (pies[clickedPieIndex].IsPointOnPie(curserLocationInForm.X, curserLocationInForm.Y))
                    {
                        pies[clickedPieIndex].AnimateSize(percentOfOldSizeForAnimation, animationDurationMS);
                        animating = true;
                        currentPieIndex = clickedPieIndex + 1;
                        tmrAnimationStarter.Interval = animationDurationMS;
                        tmrAnimationStarter.Start();
                        break;
                    }
                }
                
            }
            
           
        }
        private void TmrAnimationStarter_Elapsed(object sender, System.Timers.ElapsedEventArgs e)
        {
            tmrAnimationStarter.Interval = animationDurationMS / 3;
            if (currentPieIndex > pies.Length - 1)
                currentPieIndex = 0;
            if (currentPieIndex != clickedPieIndex)
            {
                

                pies[currentPieIndex].AnimateSize(percentOfOldSizeForAnimation, animationDurationMS);
                currentPieIndex++;
                tmrAnimationStarter.Start();
            }
            else
               animating = false;
            



        }

        private void Form1_SizeChanged(object sender, EventArgs e)
        {
           
            screenCenterX = ClientSize.Width / 2;
            screenCenterY = ClientSize.Height / 2;
            foreach (FilledPie fp in pies)
            {
               
                fp.X = screenCenterX - fp.Width /2;
                fp.Y = screenCenterY - fp.Height / 2;
            }
            
        }

        private void tmrMainAnimation_Tick(object sender, EventArgs e)
        {
            foreach (FilledPie fp in pies)
            {
                if (fp.NumOfTicksNeeded > 0)
                {
                    fp.CurrentWidthToAdd += fp.WidthPerTick;
                    fp.CurrentHeightToAdd += fp.HeightPerTick;
                    if (fp.CurrentWidthToAdd >= 2 || fp.CurrentWidthToAdd <= -2)
                    {
                        fp.Width += ((int)fp.CurrentWidthToAdd);
                        fp.X -= ((int)fp.CurrentWidthToAdd) / 2;
                        if (((int)fp.CurrentWidthToAdd) % 2 == 0)

                            fp.CurrentWidthToAdd = fp.CurrentWidthToAdd % 1;
                        else
                        {
                            fp.Width--;
                            fp.CurrentWidthToAdd = fp.CurrentWidthToAdd % 1 + 1;
                        }
                    }

                    if (fp.CurrentHeightToAdd >= 2 || fp.CurrentHeightToAdd <= -2)
                    {
                        fp.Height += ((int)fp.CurrentHeightToAdd);
                        fp.Y -= ((int)fp.CurrentHeightToAdd) / 2;

                        if (((int)fp.CurrentHeightToAdd) % 2 == 0)
                            fp.CurrentHeightToAdd = fp.CurrentHeightToAdd % 1;
                        else
                        {
                            fp.Height--;
                            fp.CurrentHeightToAdd = fp.CurrentHeightToAdd % 1 + 1;
                        }
                    }




                    fp.NumOfTicksNeeded--;
                }
            }
            Invalidate();
        }
    }
}
