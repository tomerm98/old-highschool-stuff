using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace Shlomi
{
    public partial class Form1 : Form
    {
        const int numOfPies = 8;
        const int animationTickRate = 30;
        const int animationDuration = 1000;
        const float percentOfRectSizeFromScreenHeight = 25F;
        const float percentOfOldSizeForAnimation = 150F;
        int screenCenterX;
        int screenCenterY;
        int rectWidth;
        int rectHeight;


        FilledPie fp;
        Graphics graphics;
        Rectangle[] rects;
        FilledPie[] pies;

        public Form1()
        {
            InitializeComponent();
        }



        private void Form1_Load(object sender, EventArgs e)
        {

            // MessageBox.Show((Math.Atan2(15,-22)) + "");

            graphics = CreateGraphics();
            fp = new FilledPie(new Rectangle(100,100,100,100),-90,90,animationTickRate);
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
                if (startAngle > 180)
                    startAngle = 0 - (startAngle - 180);
                pies[i] = new FilledPie(brushes[i],rects[i], startAngle, sweepAngle, animationTickRate);
            }


            timer1.Interval = animationTickRate;
            timer1.Start();


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
                graphics.FillPie(fp.Brush, fp.Rect, fp.StartAngle, fp.SweepAngle);

         }




        private void timer1_Tick(object sender, EventArgs e)
        {
            Invalidate();

        }


        private void Form1_Click(object sender, EventArgs e)
        {

             Point curserLocationInForm = PointToClient(Cursor.Position);
            foreach (FilledPie fp in pies)
            {
                if (fp.IsPointOnPie(curserLocationInForm.X, curserLocationInForm.Y))
                {
                    fp.AnimateSize(percentOfOldSizeForAnimation, animationDuration);
                    break;
                }
            }
           
        }

        private void Form1_SizeChanged(object sender, EventArgs e)
        {

        }
    }
}
