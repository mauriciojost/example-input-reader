package example;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

public class CustomInputFormat extends FileInputFormat<LongWritable, Text> {

    // https://hadoopi.wordpress.com/2013/05/27/understand-recordreader-inputsplit/
    @Override
    public RecordReader<LongWritable, Text>
    createRecordReader(InputSplit split, TaskAttemptContext context) {
        return new CustomRecordReader();
    }

    @Override
    protected boolean isSplitable(JobContext context, Path file) {
        return true;
    }

}