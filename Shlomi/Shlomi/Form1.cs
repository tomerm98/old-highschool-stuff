using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing;

namespace Shlomi
{
    public partial class Form1 : Form
    {
        static bool up = true;
        public Form1()
        {
            InitializeComponent();
        }

       
        private void Form1_Load(object sender, EventArgs e)
        {
            
        }

      

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            Graphics graphics = CreateGraphics();
            SolidBrush brush = new SolidBrush(Color.Red);
            int width = (int)numericUpDown3.Value;
            int height = (int)numericUpDown4.Value;
            Rectangle rect = new Rectangle(100, 100, width, height);
            float startAngle = (float)numericUpDown1.Value;
            float sweepAngle = (float)numericUpDown2.Value;
            graphics.FillPie(brush, rect, startAngle, sweepAngle);
            
        }

        

        private void numericUpDown2_ValueChanged(object sender, EventArgs e)
        {
           
        }

        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {
           
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            Invalidate();
        }

        private void timer2_Tick(object sender, EventArgs e)
        {
            if (numericUpDown2.Value == 0)
                up = true;
            if (numericUpDown2.Value == 360)
                up = false;
            if (up)
                numericUpDown2.Value++;
            else numericUpDown2.Value--;
        }
    }
}
