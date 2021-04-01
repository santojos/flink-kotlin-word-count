package com.example

import org.apache.commons.compress.utils.Lists
import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.ExecutionEnvironment
import org.apache.flink.api.java.functions.FlatMapIterator
import org.apache.flink.api.java.operators.DataSource
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.util.Collector
import java.lang.Exception


fun main(args: Array<String>) {

    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment()
    var dataSource: DataSource<String> = env.readTextFile("/tmp/worldcount.txt")


    dataSource.flatMap( FlatMapFunction { value: String, out: Collector<Tuple2<String, Int>>  -> value.split(" ").iterator().forEach { out.collect(Tuple2(it, 1)) } })
        .groupBy(0)
        .sum(1)
        .print()

}