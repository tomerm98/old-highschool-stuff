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
        public const int numOfPies = 8;
        
        public const int animationTickRate = 30;


        FilledPie fp;
        Graphics graphics;
        public Form1()
        {
            InitializeComponent();
        }


        
        private void Form1_Load(object sender, EventArgs e)
        {
            
           // MessageBox.Show((Math.Atan2(15,-22)) + "");

            graphics = CreateGraphics();
            fp = new FilledPie(new Rectangle(100,100,100,100),90,90,animationTickRate);
            Rectangle[] rects = new Rectangle[numOfPies];
            foreach (Rectangle rect in rects)
            {
                rect = new 
            }
            timer1.Interval = animationTickRate;
            timer1.Start();


        }

      

        private void Form1_Paint(object sender, PaintEventArgs e)
        {

            graphics.FillPie(fp.Brush, fp.Rect, fp.StartAngle, fp.SweepAngle);

        }
        

       

        private void timer1_Tick(object sender, EventArgs e)
        {
            Invalidate();
            numericUpDown1.Value = (decimal) fp.StartAngle;
            numericUpDown2.Value = (decimal)fp.SweepAngle;
            numericUpDown3.Value = fp.Rect.Height;
            numericUpDown4.Value = fp.Rect.Width;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            fp.AnimateSize(50, 1000);
            
        }

        private void Form1_Click(object sender, EventArgs e)
        {
            
            Point curserLocationInForm = PointToClient(Cursor.Position);
            //MessageBox.Show(curserLocationInForm.X + " " + curserLocationInForm.Y);
            MessageBox.Show(fp.IsPointOnPie(curserLocationInForm.X, curserLocationInForm.Y).ToString());
        }
    }
}
