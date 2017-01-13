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
        static bool up = true;
        public const int animationTickRate = 30;
        FilledPie fp;
        Graphics graphics;
        public Form1()
        {
            InitializeComponent();
        }

       
        private void Form1_Load(object sender, EventArgs e)
        {
            timer1.Interval = animationTickRate;
            graphics = CreateGraphics();
            Rectangle rect = new Rectangle(100, 100, 100, 100);
            fp = new FilledPie(rect, 90, 90, animationTickRate);
           

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
    }
}
